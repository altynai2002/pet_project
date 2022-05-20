package com.pro.petproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected var compositeDisposable: CompositeDisposable =  CompositeDisposable()

    protected val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?>
        get() = _event

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clearEvents() {
        _event.value = null
    }
}