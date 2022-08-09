package com.dias.githubapidemo.ui.listuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.network.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUserViewModel : ViewModel() {
    // listUser digunakna sebagai penampung data setelah diambil dari API
    private val _listUser = MutableLiveData<List<User>>()

    // getter listUser yang akan menyediakan data yang akan ditampilkan di UI
    val listUser get() = _listUser as LiveData<List<User>>

    fun getUsersList() {
        GithubApi.getGithubApi().getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                // data sudah berada di parameter response ketika fungsi getUsersList() dipanggil
                response: Response<List<User>>,
            ) {
                // mengisi listUser dengan data yang diambil dari API
                if (response.isSuccessful)
                    _listUser.value = response.body()
                // alternatif
                // _listUser.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("ListUserViewModel", "onFailure: ${t.message}")
            }

        })
    }
}