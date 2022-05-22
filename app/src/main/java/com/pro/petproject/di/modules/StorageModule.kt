package com.pro.petproject.di.modules

import android.app.Application
import androidx.room.Room
import com.pro.petproject.data.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {


    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    fun providePostDao(appDatabase: AppDatabase) = appDatabase.postDao()


    @Provides
    @Singleton
    fun provideAppDatabase(context: Application) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

//    @Provides
//    @Singleton
//    fun provideAppDatabasePost(context: Application) =
//        Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            AppDatabase.DB_NAME
//        )
//            .fallbackToDestructiveMigration()
//            .build()
}