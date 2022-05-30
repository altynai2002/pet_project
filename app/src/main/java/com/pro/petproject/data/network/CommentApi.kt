package com.pro.petproject.data.network

import com.pro.petproject.data.models.CommentDto
import com.pro.petproject.data.models.PostDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface CommentApi {
    @GET("data/WmComments")
    fun getComments(): Observable<List<CommentDto>>

    @POST("data/WmComments")
    fun saveComment(
        @Body comment: CommentDto
    ): Observable<Unit>

}