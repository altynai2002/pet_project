package com.pro.petproject.data.network

import com.pro.petproject.data.models.UserDto
import com.pro.petproject.domain.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("data/Users")
    fun getUsers(): Single<List<UserDto>>

    @GET("/api/episodes/{ownerId}")
    fun getUserById(@Path("ownerId") ownerId: String): Single<List<UserDto>>
}