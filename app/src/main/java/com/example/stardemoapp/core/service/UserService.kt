package com.example.stardemoapp.core.service

import com.example.stardemoapp.main.user.model.ListUserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUser(): Single<ListUserResponse>
}