package com.example.stardemoapp.core.repository

import com.example.stardemoapp.core.service.UserService
import com.example.stardemoapp.dao.ListUserDao
import com.example.stardemoapp.main.user.model.ListUserResponse.ListUserResponseItem
import io.reactivex.Single

class UserRepository(private val service: UserService, private val userDao: ListUserDao) {
    private val listUserDB : MutableList<ListUserResponseItem> = mutableListOf()

    fun syncUser() : Single<List<ListUserResponseItem>> {
        return service.getUser()
            .flatMap {
                listUserDB.addAll(it)
                deleteAllUserDB()
                return@flatMap saveListUser(it)
            }.flatMap {
                return@flatMap getAllUser()
            }.flatMap {
                return@flatMap Single.just(it)
            }
    }

    fun deleteAllUserDB() {
        userDao.deleteAll()
    }

    fun saveListUser(listUser: MutableList<ListUserResponseItem>) : Single<List<Long>>{
        return userDao.insertListUser(listUser)
    }

    fun getAllUser() : Single<List<ListUserResponseItem>>{
        return userDao.getListUser()
    }

    fun getUserByName(str: String) : Single<List<ListUserResponseItem>> {
        return userDao.searchUser("%${str}%")
    }
}