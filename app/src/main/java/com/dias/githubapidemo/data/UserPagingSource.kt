package com.dias.githubapidemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dias.githubapidemo.network.GithubApi
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val since = params.key ?: STARTING_ID
        val data = GithubApi.getGithubApi().getUsers(since, params.loadSize)
        val nextKey = if (data.isEmpty()) null else since + params.loadSize
        val prevKey = if (since == STARTING_ID) null else since - params.loadSize
        return try {
            LoadResult.Page(
                data = data,
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
        return STARTING_ID
    }

    companion object {
        private const val STARTING_ID = 0
    }
}