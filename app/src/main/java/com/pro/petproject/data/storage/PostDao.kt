package com.pro.petproject.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pro.petproject.data.models.PostEntity
import io.reactivex.Completable

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    fun getAll(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(posts: List<PostEntity>)

    @Query("DELETE FROM PostEntity")
    fun clearTable()
}