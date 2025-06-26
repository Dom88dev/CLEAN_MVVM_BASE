package com.dom.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

interface IIntent
interface ISideEffect
interface IState {
    val isLoading: Boolean
    val errorMessage: String?
}

// 화면 처리를 위한 함수들을 포함
interface IView<S: IState, SE: ISideEffect> {
    fun render(state: S)
    fun navigate(from: String)
}

// Intent 에 따른 View 를 변경하기 위해 필요한 값들을 포함
interface IModel<S: IState, I: IIntent, SE: ISideEffect> {
    val intents: Channel<I>
    val sideEffect: Channel<SE>
    val state: StateFlow<S>
}