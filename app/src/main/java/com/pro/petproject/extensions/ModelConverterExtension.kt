package com.pro.petproject.extensions

import com.pro.petproject.data.models.*


fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        objectId = this.objectId,
        name = this.name,
        email = this.email,
        gender = this.gender,
        password = this.password,
    )
}

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        userId = this.userId,
        title = this.title,
        content = this.content,
        objectId = this.objectId
    )
}

fun CommentDto.toCommentEntity(): CommentEntity {
    return CommentEntity(
        userId = this.userId,
        objectId = this.objectId,
        postId = this.postId,
        text = this.text
    )
}