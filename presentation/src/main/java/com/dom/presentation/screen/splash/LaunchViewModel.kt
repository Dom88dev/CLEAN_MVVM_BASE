package com.dom.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.dom.presentation.base.BaseViewModel
import com.dom.presentation.screen.main.MainActivity
import com.dom.presentation.screen.splash.event.LaunchIntent
import com.dom.presentation.screen.splash.event.LaunchSideEffect
import com.dom.presentation.screen.splash.event.LaunchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(

) : BaseViewModel<LaunchState, LaunchIntent, LaunchSideEffect>() {

    override val _state: MutableStateFlow<LaunchState> = MutableStateFlow(LaunchState(false))

    override fun loading(isLoading: Boolean, errorMessage: String?) {
        updateState(state.value.copy(isLoad = isLoading, errorMsg = errorMessage))
    }

    override suspend fun handleSideEffect(sideEffect: LaunchSideEffect) {
        when (sideEffect) {
            LaunchSideEffect.NavigateToMainActivity -> updateNavigation(MainActivity::class.java.name)
        }
    }

    override suspend fun handleIntent(intent: LaunchIntent) {
        when (intent) {
            LaunchIntent.NavigateToMainActivity -> sideEffect.send(LaunchSideEffect.NavigateToMainActivity)
        }
    }

    override fun fetch(): Job = viewModelScope.launch {
        delay(3000)
        intents.send(LaunchIntent.NavigateToMainActivity)
    }
}