package com.dom.presentation.screen.main.event

import com.dom.domain.IIntent
import com.dom.domain.model.Data

sealed class MainIntent: IIntent {
    data object GetUserIntent: MainIntent()
    data class ClickUserItem(val user: Data.User): MainIntent()
}