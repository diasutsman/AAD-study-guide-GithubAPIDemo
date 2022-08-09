package com.dias.githubapidemo.network

import com.dias.githubapidemo.BuildConfig
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.data.SearchRepoResponse
import com.dias.githubapidemo.data.SearchUserResponse
import com.dias.githubapidemo.data.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String,
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    fun getUserDetails(
        @Path("username") username: String,
    ): Call<User>

    @GET("users/{username}/repos")
    fun getUserRepos(
        @Path("username") username: String,
    ): Call<List<Repo>>

    // repository
    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
    ): Call<SearchRepoResponse>

    companion object {
        fun getGithubApi(): GithubApi {
            val httpLoggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val okHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
//                        .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}")
                        .build()
                    return@addInterceptor chain.proceed(request)
                }
                .pingInterval(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GithubApi::class.java)
        }
    }
}