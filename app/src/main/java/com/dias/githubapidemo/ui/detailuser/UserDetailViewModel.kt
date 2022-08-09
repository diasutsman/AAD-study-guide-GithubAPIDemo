package com.dias.githubapidemo.ui.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.network.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user as LiveData<User>

    private val _repos = MutableLiveData<List<Repo>>()
    val repos = _repos as LiveData<List<Repo>>

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
        GithubApi.getGithubApi().getUserRepos(username).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) _repos.value = response.body()
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.d("DetailViewModel", "onFailure: ${t.message}")
            }

        })
    }
}