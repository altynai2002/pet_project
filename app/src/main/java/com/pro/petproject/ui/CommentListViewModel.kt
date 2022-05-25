package com.pro.petproject.ui

import androidx.lifecycle.LiveData
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.domain.use_cases.*
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel @Inject constructor(
    private val getCommentAsLiveDataUseCase : GetCommentAsLiveDataUseCase,
    private val getCommentUseCase: GetCommentUseCase,
): BaseViewModel() {


    val comments: LiveData<List<CommentEntity>> = getCommentAsLiveDataUseCase()
//    var postList: LiveData<List<PostEntity>> = getPostsByIdUseCase(id)

    init {
        loadComments()
    }

        fun loadComments() {
        _event.value = Event.ShowLoading
        compositeDisposable.add(
            getCommentUseCase()
                .doOnTerminate {
                    _event.value = Event.HideLoading
                }
                .subscribe({},{
                    handleError(it)
                })
        )
    }
}