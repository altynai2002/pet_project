package com.pro.petproject.ui.addComment

import com.pro.petproject.data.models.CommentDto
import com.pro.petproject.domain.use_cases.AddCommentUseCase
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class AddCommentViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
): BaseViewModel() {

    private var userId: String = "A8DE28AF-9002-415D-99CB-A5BF2A29D111"

    fun addComment(text: String?) {
        if (text.isNullOrEmpty()) {
            _event.value = Event.OnEmptyFields
            return
        }
        var randomId = (0..1000).shuffled().last()
        compositeDisposable.add(
            addCommentUseCase(CommentDto(
                userId=userId, text = text, postId = "A8DE28AF-9002-415D-99CB-A5BF2A29D111",
                objectId = randomId.toString()) )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _event.value = Event.OnRegSuccess
                }, {
                    handleError(it)
                })
        )
    }
}