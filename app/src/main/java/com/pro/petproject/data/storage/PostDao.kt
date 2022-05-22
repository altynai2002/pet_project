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
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertPosts(post: List<PostEntity>): Completable

    @Query("SELECT * FROM PostEntity")
    fun getAll(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(posts: List<PostEntity>)

//    @Query("SELECT * FROM PostEntity WHERE ownerId = :ownerId")
//    fun getPostById(ownerId : String): LiveData<PostEntity>

    @Query("DELETE FROM PostEntity")
    fun clearTable()
}