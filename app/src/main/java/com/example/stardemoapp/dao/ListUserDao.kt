package com.example.stardemoapp.dao

import androidx.room.*
import com.example.stardemoapp.main.user.model.ListUserResponse.ListUserResponseItem
import io.reactivex.Single


@Dao
interface ListUserDao {
    @Query("SELECT * FROM listUserEntity")
    fun getListUser(): Single<List<ListUserResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(users : List<ListUserResponseItem>) : Single<List<Long>>

    @Query("SELECT * FROM listUserEntity WHERE login LIKE :str")
    fun searchUser(str: String) : Single<List<ListUserResponseItem>>

    @Query("DELETE FROM listUserEntity")
    fun deleteAll()
}