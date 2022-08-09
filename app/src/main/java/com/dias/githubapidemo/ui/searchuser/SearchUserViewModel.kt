package com.dias.githubapidemo.ui.searchuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dias.githubapidemo.data.SearchUserResponse
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.network.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel : ViewModel() {

    // listUser digunakna sebagai penampung data setelah diambil dari API
    private val _listUser = MutableLiveData<List<User>>()

    // getter listUser yang akan menyediakan data yang akan ditampilkan di UI
    val listUser get() = _listUser as LiveData<List<User>>

    fun searchUser(nama: String) {
        GithubApi.getGithubApi().searchUsers(nama).enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>,
            ) {
                _listUser.value = response.body()?.items ?: listOf()
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                Log.e("SearchUserViewModel", t.message.toString())
            }

        })
    }
}