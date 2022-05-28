package com.pro.petproject.domain.use_cases

import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.repo.PostRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val postRepo: PostRepo
) {
    operator fun invoke(post: PostDto): Observable<Unit> {
        return postRepo.createPost(post)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}