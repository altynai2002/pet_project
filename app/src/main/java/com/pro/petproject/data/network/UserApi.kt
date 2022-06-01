package com.pro.petproject.data.network

import com.pro.petproject.data.models.UserDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("data/WmUsers")
    fun getUsers(): Observable<List<UserDto>>

    @GET("data/WmUsers/{objectId}")
    fun getUserById(@Path("objectId") ownerId: String): Single<List<UserDto>>

}