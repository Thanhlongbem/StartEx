package com.example.stardemoapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import com.example.stardemoapp.dao.AppDatabase

class App : Application() {
    companion object {
        private lateinit var instance: App
        fun shared(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    val appDatabase: AppDatabase by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "star-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }
}