package com.pro.petproject.domain.use_cases

import androidx.lifecycle.LiveData
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.data.repo.CommentRepo
import javax.inject.Inject

class GetCommentAsLiveDataUseCase @Inject constructor(
    private val commentRepo: CommentRepo
){
    operator fun invoke(): LiveData<List<CommentEntity>> {
        return commentRepo.getCommentsFromDB()
    }
}