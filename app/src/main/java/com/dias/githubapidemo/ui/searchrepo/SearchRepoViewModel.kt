package com.dias.githubapidemo.ui.searchrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.data.RepoPagingSource
import com.dias.githubapidemo.data.RepoPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchRepoViewModel : ViewModel() {

    private val _repoList = MutableLiveData<PagingData<Repo>>()
    val repoList : LiveData<PagingData<Repo>> get() = _repoList

    fun searchRepositories(query: String) {
//        GithubApi.getGithubApi().searchRepositories(query).enqueue(object : Callback<SearchRepoResponse> {
//            override fun onResponse(
//                call: Call<SearchRepoResponse>,
//                response: Response<SearchRepoResponse>,
//            ) {
//                  if (response.isSuccessful)
//                     _repoList.value = response.body()?.items as List<Repo>
//            }
//
//            override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
//                Log.d("SearchRepoViewModel", "onFailure: $t")
//            }
//
//        })
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = PAGE_SIZE,
                    maxSize = PAGE_SIZE * 3
                ),
                pagingSourceFactory = { RepoPagingSource(query) }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _repoList.value = it
            }
        }
    }
}