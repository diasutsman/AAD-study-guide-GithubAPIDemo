package com.dias.githubapidemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dias.githubapidemo.network.GithubApi
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val query: String = "") : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val start = params.key ?: if (query.isEmpty()) STARTING_ID else STARTING_PAGE

        return try {
            if (query.isEmpty()) {
                val data = GithubApi.getGithubApi().getUsers(start, params.loadSize)
                LoadResult.Page(
                    data = data,
                    prevKey = if (start == STARTING_ID) null else start - params.loadSize,
                    nextKey = if (data.isEmpty()) null else start + params.loadSize
                )
            } else {
                val data = GithubApi.getGithubApi()
                    .searchUsers(query, start, params.loadSize).items as List<User>
                LoadResult.Page(
                    data = data,
                    prevKey = if (start == STARTING_PAGE) null else start - 1,
                    nextKey = if (data.isEmpty()) null else start + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int {
        return STARTING_ID
    }

    companion object {
        private const val STARTING_ID = 0
        private const val STARTING_PAGE = 1
        const val PAGE_SIZE = 100
    }
}