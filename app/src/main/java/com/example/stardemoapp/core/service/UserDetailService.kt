package com.example.stardemoapp.core.service

import com.example.stardemoapp.main.userdetail.model.FollowerResponse
import com.example.stardemoapp.main.userdetail.model.ReposResponse
import com.example.stardemoapp.main.userdetail.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailService {
    @GET("users/{user}")
    fun getUser(@Path("user")  user: String): Single<UserResponse>

    @GET("users/{user}/followers")
    fun getFollower(@Path("user")  user: String): Single<FollowerResponse>

    @GET("users/{user}/repos")
    fun getRepos(@Path("user")  user: String): Single<ReposResponse>
}