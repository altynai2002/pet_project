package com.pro.petproject.data.network

import com.pro.petproject.data.models.CommentDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentApi {
    @GET("data/WmComments")
    fun getComments(): Observable<List<CommentDto>>

    @GET("data/WmComments/{objectId}")
    fun getCommentById(@Path("objectId") ownerId: String): Single<List<CommentDto>>
}