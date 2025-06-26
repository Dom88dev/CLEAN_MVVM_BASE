package com.dom.presentation.screen.detail.event

import com.dom.domain.IState
import com.dom.domain.model.Data

data class UserDetailState(
    val user: Data.User?,
    val isLoad: Boolean = false,
    val errorMsg: String? = null,
) : IState {
    override val isLoading: Boolean
        get() = isLoad
    override val errorMessage: String?
        get() = errorMsg
}