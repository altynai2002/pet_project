package com.pro.petproject.extensions

import com.pro.petproject.data.models.PostDto
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.data.models.UserDto
import com.pro.petproject.data.models.UserEntity
import com.pro.petproject.domain.models.User


fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        ownerId = this.ownerId,
        name = this.name,
        email = this.email,
        gender = this.gender,
        password = this.password,
    )
}

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        ownerId = this.ownerId,
        title = this.title,
        content = this.content,
        objectId = this.objectId
    )
}