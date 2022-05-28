package com.pro.petproject.data.repo

import com.pro.petproject.data.models.CommentDto
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.network.CommentApi
import com.pro.petproject.data.storage.CommentDao
import io.reactivex.Single
import javax.inject.Inject

class CommentRepo@Inject constructor(
    private var commentApi: CommentApi,
    private var commentDao: CommentDao
) {
    fun getCommentsFromApi(postId: String) = commentApi.getComments(postId)

//    fun getUserById(ownerId : String) = postDao.getUserById(ownerId)

//    fun getCommentWithId(postId: String): Single<List<CommentDto>> {
//        return commentApi.getCommentById(postId)
//    }


    fun getCommentsFromDB() = commentDao.getAll()

    fun saveCommentsToDb(comments: List<CommentEntity>) = commentDao.insertList(comments)
}