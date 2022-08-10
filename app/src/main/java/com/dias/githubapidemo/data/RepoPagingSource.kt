package com.dias.githubapidemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dias.githubapidemo.network.GithubApi
import retrofit2.HttpException
import java.io.IOException

class RepoPagingSource(private val query: String, private val isSearching: Boolean = true) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val page = params.key ?: STARTING_PAGE
        val githubApi = GithubApi.getGithubApi()
        val data = if(isSearching) githubApi.searchRepositories(query, page, params.loadSize).items else githubApi.getUserRepos(query, page, PAGE_SIZE)
        val nextKey = if (data.isNullOrEmpty()) null else page + 1
        val prevKey = if (page == STARTING_PAGE) null else page - 1

        return try {
            LoadResult.Page(
                data = data as List<Repo>,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int {
        return STARTING_PAGE
    }

    companion object {
        private const val STARTING_PAGE = 1
        const val PAGE_SIZE = 100
    }
}