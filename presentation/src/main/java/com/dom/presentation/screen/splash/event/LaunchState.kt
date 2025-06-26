package com.dom.presentation.screen.splash.event

import com.dom.domain.IState

data class LaunchState(
    val isReadyToMainActivity: Boolean,
    val isLoad: Boolean = false,
    val errorMsg: String? = null,
): IState {
    override val isLoading: Boolean
        get() = isLoad
    override val errorMessage: String?
        get() = errorMsg
}