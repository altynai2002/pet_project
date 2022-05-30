package com.pro.petproject.ui.registration

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pro.petproject.R
import com.pro.petproject.domain.use_cases.GetUserUseCase
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel@Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val auth: FirebaseAuth
): BaseViewModel() {

//    fun customFunction() {
//        sharedPreferences.edit().putString("email", "this is the content").apply()
//        sharedPreferences.edit().putString("password", "this is the content").apply()
//
////        val firstStoredString = sharedPreferences.getString("firstStoredString", "")
//    }

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

    fun registerNewUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.e("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.e("TAG", "createUserWithEmail:failure", task.exception)
                    Event.ShowToast(R.string.authentication_failed)
                }
            }
    }
}