package com.pro.petproject.domain.use_cases

import com.pro.petproject.data.models.CommentDto
import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.repo.CommentRepo
import com.pro.petproject.data.repo.PostRepo
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val commentRepo: CommentRepo
) {
    operator fun invoke(comment: CommentDto): Observable<Unit> {
        return commentRepo.createComment(comment)
            .subscribeOn(Schedulers.io())
    }
}