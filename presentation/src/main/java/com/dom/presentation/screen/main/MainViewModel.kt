package com.dom.presentation.screen.main

import com.dom.domain.model.Data
import com.dom.domain.usecase.UseCaseUsers
import com.dom.presentation.base.BaseViewModel
import com.dom.presentation.screen.detail.UserDetailActivity
import com.dom.presentation.screen.main.event.MainIntent
import com.dom.presentation.screen.main.event.MainSideEffect
import com.dom.presentation.screen.main.event.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCaseUsers: UseCaseUsers
) : BaseViewModel<MainState, MainIntent, MainSideEffect>(){
    override val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState(listOf()))

    var targetUser: Data.User? = null

    override fun loading(isLoading: Boolean, errorMessage: String?) {
        updateState(state.value.copy(isLoad = isLoading, errorMsg = errorMessage))
    }

    override suspend fun handleSideEffect(sideEffect: MainSideEffect) {
        when(sideEffect) {
            is MainSideEffect.ClickUserItem -> updateNavigation(UserDetailActivity::class.java.name)
        }
    }

    override suspend fun handleIntent(intent: MainIntent) {
        when(intent) {
            is MainIntent.ClickUserItem -> {
                targetUser = intent.user
                sideEffect.send(MainSideEffect.ClickUserItem)
            }
            MainIntent.GetUserIntent -> {
                launchBackTask {
                    updateState(MainState(useCaseUsers.execute()))
                }
            }
        }
    }

    override fun fetch(): Job = vScope.launch {
        intents.send(MainIntent.GetUserIntent)
    }

}