package com.example.stardemoapp.main.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.stardemoapp.base.BaseViewModel
import com.example.stardemoapp.core.repository.UserRepository
import com.example.stardemoapp.extensions.applyOn
import com.example.stardemoapp.extensions.disposedBy
import com.example.stardemoapp.main.user.model.ListUserResponse
import com.example.stardemoapp.core.rest.ResponseData

class UserViewModel(private val repository: UserRepository) : BaseViewModel() {
    val listUserLiveData = MutableLiveData<ResponseData<List<ListUserResponse.ListUserResponseItem>>>()

    fun syncUser() {
        listUserLiveData.postValue(ResponseData.loading(null))
        repository.syncUser()
            .applyOn()
            .subscribe({
                listUserLiveData.postValue(ResponseData.success(it))
            },{
                listUserLiveData.postValue(ResponseData.error("Something Went Wrong. Error message " + it.message, null))
            }).disposedBy(compositeDisposable)
    }

    fun getAllUser() {
        listUserLiveData.postValue(ResponseData.loading(null))
        repository.getAllUser()
            .applyOn()
            .subscribe({
                listUserLiveData.postValue(ResponseData.success(it))
            },{
                listUserLiveData.postValue(ResponseData.error("Something Went Wrong. Error message " + it.message, null))
            }).disposedBy(compositeDisposable)
    }

    fun getUserByName(name: String) {
        listUserLiveData.postValue(ResponseData.loading(null))
        repository.getUserByName(name)
            .applyOn()
            .subscribe({
                listUserLiveData.postValue(ResponseData.success(it))
            },{
                listUserLiveData.postValue(ResponseData.error("Something Went Wrong. Error message " + it.message, null))
            }).disposedBy(compositeDisposable)
    }
}