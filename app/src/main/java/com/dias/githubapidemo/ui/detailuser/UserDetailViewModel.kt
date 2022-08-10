package com.dias.githubapidemo.ui.detailuser

import android.util.Log
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
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.network.GithubApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user as LiveData<User>

    private val _repos = MutableLiveData<PagingData<Repo>>()
    val repos = _repos as LiveData<PagingData<Repo>>

    fun getUserDetail(username: String) {
        GithubApi.getGithubApi().getUserDetails(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) _user.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("DetailViewModel", "onFailure: ${t.message}")
            }

        })
    }

    fun getUserRepos(username: String) {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = RepoPagingSource.PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = RepoPagingSource.PAGE_SIZE,
                    maxSize = RepoPagingSource.PAGE_SIZE * 3
                ),
                pagingSourceFactory = { RepoPagingSource(username, false) }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _repos.value = it
            }
        }
    }
}