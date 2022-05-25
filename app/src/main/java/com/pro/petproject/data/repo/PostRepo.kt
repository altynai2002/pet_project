package com.pro.petproject.data.repo


import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.network.PostApi
import com.pro.petproject.data.storage.PostDao
import io.reactivex.Single
import javax.inject.Inject

class PostRepo @Inject constructor(
    @com.pro.petproject.di.annotations.PostApi
    private var postApi: PostApi,
    private var postDao: PostDao
) {

    fun getPostsFromApi() = postApi.getPosts()

//    fun getUserById(ownerId : String) = postDao.getUserById(ownerId)

    fun getPostWithId(ownerId: String): Single<List<PostDto>> {
        return postApi.getPostById(ownerId)
    }

    fun getPostsFromDB() = postDao.getAll()

    fun savePostsToDb(posts: List<PostEntity>) = postDao.insertList(posts)
}