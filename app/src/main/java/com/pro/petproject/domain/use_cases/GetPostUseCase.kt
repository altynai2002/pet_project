package com.pro.petproject.domain.use_cases

import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.repo.PostRepo
import com.pro.petproject.extensions.toPostEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepo: PostRepo
) {
    operator fun invoke(): Observable<Unit> {
        return postRepo.getPostsFromApi()
            .subscribeOn(Schedulers.io())
//            .map {
////                Thread.sleep(1000)
//                it
//            }
            .map {
                val listPost = mutableListOf<PostEntity>()
                it.forEach {
                    listPost.add(it.toPostEntity())
                }
                listPost.toList()
            }
            .map {
                postRepo.savePostsToDb(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}