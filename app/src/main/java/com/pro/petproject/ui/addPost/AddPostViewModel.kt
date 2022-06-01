package com.pro.petproject.ui.addPost

import com.pro.petproject.data.models.PostDto
import com.pro.petproject.domain.use_cases.AddPostUseCase
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
): BaseViewModel() {

    private var userId: String = "103D6211-A32D-4D3E-B124-4AFEEF439D6F"

    fun addPost(title: String?, content: String) {
        if (title.isNullOrEmpty()) {
            _event.value = Event.OnEmptyFields
            return
        }
        val randomId = (0..1000).shuffled().last()
        compositeDisposable.add(
            addPostUseCase(PostDto(userId=userId, content = content, title = title,
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