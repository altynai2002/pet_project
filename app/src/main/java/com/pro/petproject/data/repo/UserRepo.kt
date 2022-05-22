package com.pro.petproject.data.repo

import com.pro.petproject.data.models.UserDto
import com.pro.petproject.data.models.UserEntity
import com.pro.petproject.data.network.UserApi
import com.pro.petproject.data.storage.UserDao
import com.pro.petproject.domain.models.User
import io.reactivex.Single
import javax.inject.Inject

class UserRepo @Inject constructor(
    private var userDao: UserDao,
    @com.pro.petproject.di.annotations.UserApi
    private var userApi: UserApi
) {

    fun getUserFromApi() = userApi.getUsers()

    fun getUserById(ownerId : String) = userDao.getUserById(ownerId)

    fun getUserWithId(ownerId: String): Single<List<UserDto>> {
        return userApi.getUserById(ownerId)
    }

    fun clearTable() {
        userDao.clearTable()
    }

    fun saveUsersToDb(users: List<UserEntity>) = userDao.insertUsers(users)
}