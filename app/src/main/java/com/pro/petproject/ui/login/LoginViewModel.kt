package com.pro.petproject.ui.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pro.petproject.domain.use_cases.GetUserUseCase
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val auth: FirebaseAuth
): BaseViewModel() {

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("TAG", "loginUserWithEmail:success")
                } else {
                    Log.e("TAG", "loginUserWithEmail:failed")
                }
            }
    }

//    private val _user =  MutableLiveData<List<User>>()
//    val user: LiveData<List<User>>
//        get() = _user

//    private var id: String = "811CD10F-49FE-4F90-9439-1EEE1F5A4F9F"
//    fun getUser(){
//        compositeDisposable.add(
//            getUserUseCase(id)
//                .subscribe({
//                    _event.value = Event.FetchedEpisode(it)
//                },{
////                    _event.value = BaseEvent.ShowToast(it.message ?: "")
//                })
//        )
//    }
}

