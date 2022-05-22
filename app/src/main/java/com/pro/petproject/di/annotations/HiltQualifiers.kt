package com.pro.petproject.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserApi