package com.dom.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber

abstract class BaseViewModel: ViewModel() {

    protected val job: Job = SupervisorJob()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        var errorMessage = ""
        if (throwable.localizedMessage != null) errorMessage = throwable.localizedMessage!!
        for (stackTrace in throwable.stackTrace) {
            errorMessage += "\nfile : ${stackTrace.fileName} / class : ${stackTrace.className} / line : ${stackTrace.lineNumber}"
        }
        Timber.e("Occur Coroutines Error : $errorMessage")
        throwable.printStackTrace()
        errorOccur(errorMessage)
    }

    protected val vScope = viewModelScope + job + coroutineExceptionHandler

    protected val _snackBar = MutableStateFlow<String?>(null)

    /**
     * Request a snackbar to display a string.
     */
    val snackbar = _snackBar.asStateFlow()

    protected val _spinner = MutableStateFlow(false)

    /**
     * Show a loading spinner if true
     */
    val spinner: StateFlow<Boolean>
        get() = _spinner

    fun errorOccur(errorMsg: String) {
        vScope.launch {
            _snackBar.value = errorMsg
        }
    }

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }

    /**
     *  화면 초기화 시 가져올 데이터를 패치하는 함수 오버라이드로 구현
     */
    open fun fetch(): Job = vScope.launch {  }

    // 메인 처리 작업(api 호출 등은 해당 함수내에서 백그라운드 처리하기 때문에 여기서 호출해도 이상 없음)
    protected fun launchTask(block: suspend () -> Unit) {
        vScope.launch {
            try {
                block()
            } catch (error: Exception) {
                Timber.e("launchBackTask: Error = ${error.message}")
                error.printStackTrace()
                _snackBar.value = error.message
            }
        }
    }

    // 백그라운드 작업
    protected fun launchBackTask(block: suspend () -> Unit) {
        vScope.launch(Dispatchers.Default) {
            try {
                _spinner.value = job.children.any { it.isActive }
                block()
            } catch (error: Exception) {
                Timber.e("launchBackTask: Error = ${error.message}")
                error.printStackTrace()
                _snackBar.value = error.message
            } finally {
                _spinner.value = job.children.all { it.isCompleted || it.isCancelled }
            }
        }
    }

    override fun onCleared() {
        job.cancelChildren()
        super.onCleared()
    }
}