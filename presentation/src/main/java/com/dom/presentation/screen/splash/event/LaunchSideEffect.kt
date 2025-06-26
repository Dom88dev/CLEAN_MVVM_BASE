package com.dom.presentation.screen.splash.event

import com.dom.domain.ISideEffect

sealed class LaunchSideEffect: ISideEffect {
    data object NavigateToMainActivity: LaunchSideEffect()
}