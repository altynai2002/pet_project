package com.pro.petproject.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pro.petproject.R
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.ui.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    protected val _event by lazy {
        MutableLiveData<Event?>()
    }

    val event: LiveData<Event?>
        get() = _event

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun handleError(it: Throwable) {
        _event.value = when(it) {
            is UnknownHostException -> Event.ShowToast(R.string.no_internet)
            else -> Event.ShowToast(R.string.app_name)
        }
    }


    fun clearEvents() {
        _event.value = null
    }
}