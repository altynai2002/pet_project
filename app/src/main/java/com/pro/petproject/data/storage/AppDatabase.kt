package com.pro.petproject.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pro.petproject.data.models.UserEntity

@TypeConverters(TypeListConverter::class)
@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun userDao(): UserDao


    companion object {
        const val DB_NAME = "wmApp.db"
    }
}