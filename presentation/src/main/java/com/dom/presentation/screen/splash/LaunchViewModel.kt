package com.dom.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.dom.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(

): BaseViewModel() {

    private val _isReadyMainScreen = MutableStateFlow<Boolean>(false)
    val isReadyMainScreen : StateFlow<Boolean>
        get() = _isReadyMainScreen

    override fun fetch(): Job = viewModelScope.launch {
        delay(3000)
        _isReadyMainScreen.value = true
    }
}