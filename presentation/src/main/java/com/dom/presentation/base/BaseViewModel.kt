package com.dom.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    open fun fetch(): Job = viewModelScope.launch {  }

    protected val jobs = mutableListOf<Job>()

    override fun onCleared() {
        jobs.forEach { it.cancel() }
        super.onCleared()
    }
}