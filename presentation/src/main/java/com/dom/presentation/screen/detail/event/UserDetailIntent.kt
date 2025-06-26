package com.dom.presentation.screen.detail.event

import com.dom.domain.IIntent
import com.dom.domain.model.Data

sealed class UserDetailIntent: IIntent {
    data class SetUserData(val data: Data.User): UserDetailIntent()
}