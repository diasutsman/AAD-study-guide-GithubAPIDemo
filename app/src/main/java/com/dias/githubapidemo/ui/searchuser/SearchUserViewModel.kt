package com.dias.githubapidemo.ui.searchuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.data.UserPagingSource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchUserViewModel : ViewModel() {

    // listUser digunakna sebagai penampung data setelah diambil dari API
    private val _listUser = MutableLiveData<PagingData<User>>()

    // getter listUser yang akan menyediakan data yang akan ditampilkan di UI
    val listUser get() = _listUser as LiveData<PagingData<User>>

    fun searchUser(query: String) {
//        GithubApi.getGithubApi().searchUsers(nama).enqueue(object : Callback<SearchUserResponse> {
//            override fun onResponse(
//                call: Call<SearchUserResponse>,
//                response: Response<SearchUserResponse>,
//            ) {
//                _listUser.value = response.body()?.items ?: listOf()
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                Log.e("SearchUserViewModel", t.message.toString())
//            }
//
//        })
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = UserPagingSource.PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = UserPagingSource.PAGE_SIZE,
                    maxSize = UserPagingSource.PAGE_SIZE * 3
                ),
                pagingSourceFactory = { UserPagingSource(query) }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _listUser.value = it
            }
        }
    }
}