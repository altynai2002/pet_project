package com.pro.petproject.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pro.petproject.data.models.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM CommentEntity")
    fun getAll(): LiveData<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(comments: List<CommentEntity>)

    @Query("DELETE FROM CommentEntity")
    fun clearTable()
}