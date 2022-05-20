package com.pro.petproject.ui


import com.pro.petproject.domain.use_cases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor
    (
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {

//    private val _user =  MutableLiveData<List<User>>()
//    val user: LiveData<List<User>>
//        get() = _user

    private var id: String = "811CD10F-49FE-4F90-9439-1EEE1F5A4F9F"
    fun getUser(){
        compositeDisposable.add(
            getUserUseCase(id)
                .subscribe({
                    _event.value = Event.FetchedEpisode(it)
                },{
//                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }
}