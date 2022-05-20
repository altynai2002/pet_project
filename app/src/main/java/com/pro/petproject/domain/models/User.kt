package com.pro.petproject.domain.models

data class User(
    var ownerId: String,
    var name: String,
    var email: String,
    var gender: String,
    var password: String,
)