package com.pro.petproject.domain.use_cases

import androidx.lifecycle.LiveData
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.repo.PostRepo
import javax.inject.Inject

class GetPostAsLiveDataUseCase  @Inject constructor(
    private val postRepo: PostRepo
) {

    operator fun invoke(): LiveData<List<PostEntity>> {
        return postRepo.getPostsFromDB()
    }
}