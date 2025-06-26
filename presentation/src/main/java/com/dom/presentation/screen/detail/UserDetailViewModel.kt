package com.dom.presentation.screen.detail

import com.dom.presentation.base.BaseViewModel
import com.dom.presentation.screen.detail.event.UserDetailIntent
import com.dom.presentation.screen.detail.event.UserDetailSideEffect
import com.dom.presentation.screen.detail.event.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(): BaseViewModel<UserDetailState, UserDetailIntent, UserDetailSideEffect>() {
    override val _state: MutableStateFlow<UserDetailState> = MutableStateFlow(UserDetailState(null))

    override fun loading(isLoading: Boolean, errorMessage: String?) {
        updateState(state.value.copy(isLoad = isLoading, errorMsg = errorMessage))
    }

    override suspend fun handleSideEffect(sideEffect: UserDetailSideEffect) {}

    override suspend fun handleIntent(intent: UserDetailIntent) {
        when(intent) {
            is UserDetailIntent.SetUserData -> {
                launchBackTask {
                    updateState(UserDetailState(intent.data))
                }
            }
        }
    }
}