package com.pro.petproject.ui.comment

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

    private  var id: String = ""
    fun setId(id: String?){
        this.id = id ?: ""
    }

    private var postId: String = "82541599-09ED-4CF9-B53E-6E75F6DF6A20"

        fun loadComments() {
        _event.value = Event.ShowLoading
        compositeDisposable.add(
            getCommentUseCase(postId)
                .doOnTerminate {
                    _event.value = Event.HideLoading
                }
                .subscribe({},{
                    handleError(it)
                })
        )
    }
}