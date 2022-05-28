package com.pro.petproject.ui

import androidx.annotation.StringRes
import com.pro.petproject.data.models.UserDto
import com.pro.petproject.data.models.UserEntity


sealed class Event() {
    class ShowToast(@StringRes val resId: Int): Event()
    object ShowLoading: Event()
    object OnEmptyFields: Event()
    object OnRegSuccess: Event()
    object HideLoading: Event()
    class FetchedUser(val user: UserEntity): Event()
}