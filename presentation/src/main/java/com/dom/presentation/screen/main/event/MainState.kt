package com.dom.presentation.screen.main.event

import com.dom.domain.IState
import com.dom.domain.model.Data

data class MainState(
    val users: List<Data.User>,
    val isLoad: Boolean = false,
    val errorMsg: String? = null,
): IState {
    override val isLoading: Boolean
        get() = isLoad
    override val errorMessage: String?
        get() = errorMsg
}