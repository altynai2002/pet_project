package com.pro.petproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey
    var objectId: String,
    var userId: String,
    var ownerId: String?,
    var content: String,
    var title: String,
)