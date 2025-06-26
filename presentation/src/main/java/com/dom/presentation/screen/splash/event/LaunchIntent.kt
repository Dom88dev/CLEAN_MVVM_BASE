package com.dom.presentation.screen.splash.event

import com.dom.domain.IIntent

sealed class LaunchIntent: IIntent {
    data object NavigateToMainActivity: LaunchIntent()
}