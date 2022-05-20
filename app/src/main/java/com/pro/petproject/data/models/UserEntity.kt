package com.pro.petproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    var ownerId: String,
    var name: String,
    var email: String,
    var gender: String,
    var password: String,
)

