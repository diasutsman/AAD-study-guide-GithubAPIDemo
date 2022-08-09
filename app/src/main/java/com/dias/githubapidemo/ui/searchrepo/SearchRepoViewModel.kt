package com.dias.githubapidemo.ui.searchrepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.data.SearchRepoResponse
import com.dias.githubapidemo.network.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepoViewModel : ViewModel() {

    private val _repoList = MutableLiveData<List<Repo>>()
    val repoList : LiveData<List<Repo>> get() = _repoList

    fun searchRepositories(query: String) {
        GithubApi.getGithubApi().searchRepositories(query).enqueue(object : Callback<SearchRepoResponse> {
            override fun onResponse(
                call: Call<SearchRepoResponse>,
                response: Response<SearchRepoResponse>,
            ) {
                  if (response.isSuccessful)
                     _repoList.value = response.body()?.items as List<Repo>
            }

            override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
                Log.d("SearchRepoViewModel", "onFailure: $t")
            }

        })
    }
}