package com.pro.petproject.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.models.UserEntity

@TypeConverters(TypeListConverter::class)
@Database(entities = [UserEntity::class, PostEntity::class, CommentEntity::class], version = 5)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        const val DB_NAME = "wmApp.db"
    }
}
