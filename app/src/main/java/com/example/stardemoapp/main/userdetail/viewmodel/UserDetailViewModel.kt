package com.example.stardemoapp.main.userdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.stardemoapp.base.BaseViewModel
import com.example.stardemoapp.core.repository.UserDetailRepository
import com.example.stardemoapp.core.rest.ResponseData
import com.example.stardemoapp.extensions.applyOn
import com.example.stardemoapp.extensions.disposedBy
import com.example.stardemoapp.main.userdetail.model.FollowerResponse
import com.example.stardemoapp.main.userdetail.model.ReposResponse
import com.example.stardemoapp.main.userdetail.model.UserResponse

class UserDetailViewModel(private val repository: UserDetailRepository) : BaseViewModel() {
    val userData = MutableLiveData<ResponseData<UserResponse>>()
    val followerData = MutableLiveData<ResponseData<FollowerResponse>>()
    val reposData = MutableLiveData<ResponseData<ReposResponse>>()


    fun getUerData(user: String) {
        userData.postValue(ResponseData.loading(null))
        repository.getUserInfo(user)
            .applyOn()
            .subscribe({
                userData.postValue(ResponseData.success(it))
            },{
                userData.postValue(
                    ResponseData.error(
                        "Something Went Wrong. Error message " + it.message,
                        null
                    )
                )
            }).disposedBy(compositeDisposable)
    }

    fun getFollowers(user: String) {
        followerData.postValue(ResponseData.loading(null))
        repository.getFollower(user)
            .applyOn()
            .subscribe({
                followerData.postValue(ResponseData.success(it))
            }, {
                followerData.postValue(
                    ResponseData.error(
                        "Something Went Wrong. Error message " + it.message,
                        null
                    )
                )
            }).disposedBy(compositeDisposable)
    }

    fun getRepos(user: String) {
        reposData.postValue(ResponseData.loading(null))
        repository.getRepos(user)
            .applyOn()
            .subscribe({
                reposData.postValue(ResponseData.success(it))
            }, {
                reposData.postValue(
                    ResponseData.error(
                        "Something Went Wrong. Error message " + it.message,
                        null
                    )
                )
            }).disposedBy(compositeDisposable)
    }

}