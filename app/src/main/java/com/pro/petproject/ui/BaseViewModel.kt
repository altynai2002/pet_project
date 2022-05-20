package com.pro.petproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pro.petproject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected var compositeDisposable: CompositeDisposable =  CompositeDisposable()

    protected val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?>
        get() = _event

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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clearEvents() {
        _event.value = null
    }
}