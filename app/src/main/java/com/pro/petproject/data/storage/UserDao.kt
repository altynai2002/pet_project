package com.pro.petproject.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pro.petproject.data.models.UserEntity
import io.reactivex.Completable


@Dao
interface UserDao {
    @Insert
    fun insertUsers(user: List<UserEntity>): Completable

    @Query("SELECT * FROM UserEntity")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE ownerId = :ownerId")
    fun getUserById(ownerId : String): LiveData<UserEntity>

    @Query("DELETE FROM UserEntity")
    fun clearTable()
}