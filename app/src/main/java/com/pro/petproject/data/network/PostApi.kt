package com.pro.petproject.data.network

import com.pro.petproject.data.models.PostDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {
    @GET("data/WmPosts")
    fun getPosts(): Observable<List<PostDto>>

    @GET("data/WmPosts/{objectId}")
    fun getPostById(@Path("objectId") objectId: String): Single<List<PostDto>>

    @POST("data/WmPosts")
    fun savePost(
        @Body post: PostDto
    ): Observable<Unit>
}