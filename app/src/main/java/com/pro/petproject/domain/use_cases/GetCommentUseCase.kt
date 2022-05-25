package com.pro.petproject.domain.use_cases

import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.data.repo.CommentRepo
import com.pro.petproject.extensions.toCommentEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCommentUseCase@Inject constructor(
    private val commentRepo: CommentRepo
) {
    operator fun invoke(): Observable<Unit> {
        return commentRepo.getCommentsFromApi()
            .subscribeOn(Schedulers.io())
            .map {
                val listComment = mutableListOf<CommentEntity>()
                it.forEach {
                    listComment.add(it.toCommentEntity())
                }
                listComment.toList()
            }
            .map {
                commentRepo.saveCommentsToDb(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}