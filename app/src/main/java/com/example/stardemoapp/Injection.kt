package com.example.stardemoapp

import com.example.stardemoapp.core.repository.UserDetailRepository
import com.example.stardemoapp.core.repository.UserRepository
import com.example.stardemoapp.core.rest.RestService
import com.example.stardemoapp.core.service.UserDetailService
import com.example.stardemoapp.core.service.UserService
import com.example.stardemoapp.main.user.viewmodel.UserViewModel
import com.example.stardemoapp.main.userdetail.viewmodel.UserDetailViewModel

object Injection {

    fun provideUserViewModel() : UserViewModel {
        val service = RestService.createService(UserService::class.java)
        val dao = App.shared().appDatabase.listUserDao()
        val repository = UserRepository(service, dao)
        return UserViewModel(repository)
    }

    fun provideUserDetailViewModel() : UserDetailViewModel {
        val service = RestService.createService(UserDetailService::class.java)
        val repository = UserDetailRepository(service)
        return UserDetailViewModel(repository)
    }
}