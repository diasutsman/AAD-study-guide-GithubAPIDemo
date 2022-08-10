package com.dias.githubapidemo.ui.listuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.data.UserPagingSource
import com.dias.githubapidemo.data.UserPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class ListUserViewModel : ViewModel() {
    // listUser digunakna sebagai penampung data setelah diambil dari API
//    private val _listUser = MutableLiveData<List<User>>()
//
//    // getter listUser yang akan menyediakan data yang akan ditampilkan di UI
//    val listUser get() = _listUser as LiveData<List<User>>
//
//    fun getUsersList() {
//        GithubApi.getGithubApi().getUsers().enqueue(object : Callback<List<User>> {
//            override fun onResponse(
//                call: Call<List<User>>,
//                // data sudah berada di parameter response ketika fungsi getUsersList() dipanggil
//                response: Response<List<User>>,
//            ) {
//                // mengisi listUser dengan data yang diambil dari API
//                if (response.isSuccessful)
//                    _listUser.value = response.body()
//                // alternatif
//                // _listUser.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                Log.d("ListUserViewModel", "onFailure: ${t.message}")
//            }
//
//        })
//    }

    val listUser: Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE,
            maxSize = PAGE_SIZE * 3
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.cachedIn(viewModelScope)
}