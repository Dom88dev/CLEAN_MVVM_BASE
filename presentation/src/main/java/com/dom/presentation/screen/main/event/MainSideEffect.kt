package com.dom.presentation.screen.main.event

import com.dom.domain.ISideEffect
import com.dom.domain.model.Data

sealed class MainSideEffect: ISideEffect {
    data object ClickUserItem: MainSideEffect()
}