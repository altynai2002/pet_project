package com.pro.petproject.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.models.UserEntity

@TypeConverters(TypeListConverter::class)
@Database(entities = [UserEntity::class, PostEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object {
        const val DB_NAME = "wmApp.db"
    }
}


//@TypeConverters(TypeListConverter::class)
//@Database(entities = [PostEntity::class], version = 1)
//abstract class AppDatabasePost: RoomDatabase()  {
//
//    abstract fun postDao(): PostDao
//
//
//    companion object {
//        const val DB_NAME = "post.db"
//    }
//}

