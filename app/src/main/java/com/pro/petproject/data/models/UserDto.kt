package com.pro.petproject.data.models

data class UserDto(
    var ownerId: String,
    var name: String,
    var email: String,
    var gender: String,
    var password: String,
)