package com.pro.petproject.ui.profile


import com.pro.petproject.domain.use_cases.GetUserUseCase
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor
    (
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {


    private var id: String = "811CD10F-49FE-4F90-9439-1EEE1F5A4F9F"
    fun getUser(){
        compositeDisposable.add(
            getUserUseCase(id)
                .subscribe({
                    _event.value = Event.FetchedUser(it)
                },{
                })
        )
    }
}