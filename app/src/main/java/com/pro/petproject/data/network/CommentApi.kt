package com.pro.petproject.data.network

import com.pro.petproject.data.models.CommentDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi {
    @GET("data/WmComments?where=postId%20%3D%20'{postId}'")
    fun getComments(@Query("postId") postId: String): Observable<List<CommentDto>>

//    fun getUserByNickname(
//        @Query("where") where: String
//    ): Observable<List<CommentDto>>

//    @GET("data/WmComments?where=postId%20%3D%20'postId'")
//    fun getCommentById(@Path("postId") ownerId: String): Single<List<CommentDto>>
}