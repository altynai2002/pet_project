package com.pro.petproject.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import com.pro.petproject.R
import com.pro.petproject.ui.BaseViewModel
import com.pro.petproject.ui.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val getEpisodeUseCase: GetPostUseCase,
//    private val getEpisodeAsLiveDataUseCase: GetPostAsLiveDataUseCase,
): BaseViewModel(){

//    val episodeLiveData: LiveData<List<Post>> = getPostAsLiveDataUseCase()

    init {
//        loadPosts()
    }

//    fun loadPosts() {
//        _event.value = Event.ShowLoading
//        compositeDisposable.add(
//            getPostUseCase()
//                .doOnTerminate{
//                    _event.value = Event.HideLoading
//                }
//                .subscribe({},{
////                    handleError(it)
//                })
//        )
//    }

    private fun handleError(it: Throwable) {
        _event.value = when(it) {
            is UnknownHostException -> Event.ShowToast(R.string.no_internet)
            else -> Event.ShowToast(R.string.app_name)
        }
    }

    //    исп-ся в гл.фрагменте
//    fun getPostByIndex(index: Int): Post? {
//        return postLiveData.value?.get(index)
//    }
}