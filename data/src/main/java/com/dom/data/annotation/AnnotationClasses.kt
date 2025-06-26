package com.dom.data.annotation

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BackTask

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoTask