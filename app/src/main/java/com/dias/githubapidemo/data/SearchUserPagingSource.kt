package com.dias.githubapidemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dias.githubapidemo.network.GithubApi
import retrofit2.HttpException
import java.io.IOException

class SearchUserPagingSource(private val query: String) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: STARTING_PAGE
        val data = GithubApi.getGithubApi().searchUsers(query, page, params.loadSize).items
        val nextKey = if (data.isNullOrEmpty()) null else page + 1
        val prevKey = if (page == STARTING_PAGE) null else page - 1

        return try {
            LoadResult.Page(
                data = data as List<User>,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int {
        return STARTING_PAGE
    }

    companion object {
        private const val STARTING_PAGE = 1
        const val PAGE_SIZE = 100
    }
}