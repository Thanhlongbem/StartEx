package com.example.stardemoapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stardemoapp.main.user.model.ListUserResponse

@Database(entities = [ListUserResponse.ListUserResponseItem::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun listUserDao() : ListUserDao
}