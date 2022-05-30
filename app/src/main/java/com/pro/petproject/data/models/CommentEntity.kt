package com.pro.petproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    @PrimaryKey
    var objectId: String,
    var postId: String,
    var userId: String,
    var text: String,
)