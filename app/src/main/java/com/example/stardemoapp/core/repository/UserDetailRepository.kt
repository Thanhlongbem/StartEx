package com.example.stardemoapp.core.repository

import com.example.stardemoapp.core.service.UserDetailService
import com.example.stardemoapp.main.userdetail.model.FollowerResponse
import com.example.stardemoapp.main.userdetail.model.ReposResponse
import com.example.stardemoapp.main.userdetail.model.UserResponse
import io.reactivex.Single

class UserDetailRepository (private val service: UserDetailService){
    fun getFollower(user: String) : Single<FollowerResponse> {
        return service.getFollower(user)
    }

    fun getRepos(user: String) : Single<ReposResponse> {
        return service.getRepos(user)
    }

    fun getUserInfo(user: String) : Single<UserResponse> {
        return  service.getUser(user)
    }
}