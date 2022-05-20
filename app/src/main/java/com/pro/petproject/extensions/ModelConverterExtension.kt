package com.pro.petproject.extensions

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