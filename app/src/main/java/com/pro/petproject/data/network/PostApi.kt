package com.pro.petproject.data.network

import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.models.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("data/Posts")
    fun getPosts(): Single<List<PostDto>>

    @GET("/api/episodes/{objectId}")
    fun getPostById(@Path("objectId") ownerId: String): Single<List<PostDto>>
}