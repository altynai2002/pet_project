package com.pro.petproject.domain.use_cases

import android.util.Log
import com.pro.petproject.data.models.UserDto
import com.pro.petproject.data.models.UserEntity
import com.pro.petproject.data.repo.UserRepo
import com.pro.petproject.domain.models.User
import com.pro.petproject.extensions.toUserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(ownerId: String): Single<UserEntity> {
        return userRepo.getUserWithId(ownerId)
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("List", it.toString())
                it.first()
            }
            .map {
                Log.d("List2", it.toString())
//                println(it)
//                userRepo.saveUsersToDb(it.map { it.toUserEntity() })
                it.toUserEntity()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}