package com.pro.petproject.ui.main

import androidx.lifecycle.LiveData
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.domain.use_cases.GetPostAsLiveDataUseCase
import com.pro.petproject.domain.use_cases.GetPostUseCase
import com.pro.petproject.domain.use_cases.GetUserUseCase
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostAsLiveDataUseCase : GetPostAsLiveDataUseCase,
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {


    val posts: LiveData<List<PostEntity>> = getPostAsLiveDataUseCase()

    init {
        loadPosts()
    }

    fun loadPosts() {
        _event.value = Event.ShowLoading
        compositeDisposable.add(
            getPostUseCase()
                .doOnTerminate {
                    _event.value = Event.HideLoading
                }
                .subscribe({

                },{
                    handleError(it)
                })
        )
    }


    fun getPostByIndex(index: String): String? {
        return posts.value?.getOrElse(index)
    }

}

private fun Any?.getOrElse(index: String): String {
    return index
}



