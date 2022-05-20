package com.pro.petproject.data.storage

import androidx.room.Dao
import androidx.room.Insert
import com.pro.petproject.data.models.UserEntity
import io.reactivex.Completable


@Dao
interface UserDao {
    @Insert
    fun insertUsers(user: List<UserEntity>): Completable
}