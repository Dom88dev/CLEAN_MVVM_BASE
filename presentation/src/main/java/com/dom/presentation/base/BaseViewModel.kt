package com.dom.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom.domain.IIntent
import com.dom.domain.IModel
import com.dom.domain.ISideEffect
import com.dom.domain.IState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber

abstract class BaseViewModel<S : IState, I : IIntent, SE : ISideEffect> : ViewModel(),
    IModel<S, I, SE> {
    override val intents: Channel<I> = Channel(Channel.UNLIMITED)
    override val sideEffect: Channel<SE> = Channel(Channel.UNLIMITED)

    protected abstract val _state: MutableStateFlow<S>
    override val state: StateFlow<S>
        get() = _state.asStateFlow()

    protected val _navigation = MutableStateFlow("")
    val navigation = _navigation.asStateFlow()

    protected val job: Job = SupervisorJob()

    protected fun updateState(newState: S) {
        _state.value = newState
    }

    protected fun updateNavigation(newNavigationString: String) {
        _navigation.value = newNavigationString
    }

    fun resetNavigation() {
        _navigation.value = ""
    }

    protected abstract fun loading(isLoading: Boolean, errorMessage: String? = null)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        var errorMessage = ""
        if (throwable.localizedMessage != null) errorMessage = throwable.localizedMessage!!
        for (stackTrace in throwable.stackTrace) {
            errorMessage += "\nfile : ${stackTrace.fileName} / class : ${stackTrace.className} / line : ${stackTrace.lineNumber}"
        }
        Timber.e("Occur Coroutines Error : $errorMessage")
        throwable.printStackTrace()
        loading(false, errorMessage)
    }


    protected val vScope = viewModelScope + job + coroutineExceptionHandler


    init {
        intentConsumer()
        sideEffectConsumer()
    }

    private fun intentConsumer() {
        vScope.launch {
            intents.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    abstract suspend fun handleIntent(intent: I)

    private fun sideEffectConsumer() {
        vScope.launch {
            sideEffect.consumeAsFlow().collect {
                handleSideEffect(it)
            }
        }
    }

    abstract suspend fun handleSideEffect(sideEffect: SE)

    /**
     *  화면 초기화 시 가져올 데이터를 패치하는 함수 오버라이드로 구현
     */
    open fun fetch(): Job = vScope.launch { }

    // 메인 처리 작업(api 호출 등은 해당 함수내에서 백그라운드 처리하기 때문에 여기서 호출해도 이상 없음)
    protected fun launchTask(block: suspend () -> Unit) {
        vScope.launch {
            try {
                block()
            } catch (error: Exception) {
                Timber.e("launchBackTask: Error = ${error.message}")
                error.printStackTrace()
                loading(false, error.message)
            }
        }
    }

    // 백그라운드 작업
    protected fun launchBackTask(block: suspend () -> Unit) {
        vScope.launch(Dispatchers.Default) {
            try {
                loading(true)
                block()
                loading(false)
            } catch (error: Exception) {
                Timber.e("launchBackTask: Error = ${error.message}")
                error.printStackTrace()
                loading(false, error.message)
            }
        }
    }

    override fun onCleared() {
        job.cancelChildren()
        super.onCleared()
    }
}