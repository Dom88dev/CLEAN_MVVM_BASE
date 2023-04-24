package com.dom.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import timber.log.Timber
import com.dom.domain.model.Result

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment() {
    abstract val vm: VM
    abstract val vb: VB
    private lateinit var fetchJob: Job
    abstract fun initViews()
    abstract fun observeData(): Job

    open fun initScreen() {
        initViews()
        fetchJob = vm.fetch()
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
    }

    fun <T : Any> handleData(
        data: Result<T>
    ) {
        when (data) {
            is Result.Failure.Error -> onApiError(data)
            is Result.Failure.Exception -> onException(data)
            Result.Loading -> onLoading()
            Result.NotInit -> {}
            is Result.Success -> {
                onSuccess(data.data)
            }
        }
    }

    /**
     * 데이터 처리 중일 때
     */
    open fun onLoading() {
        // activity에서 감지하지 않는 데이터를 감지할 경우 override 해서 progress 표시 처리 구현
    }

    /**
     * 데이터 처리 결과 성공일 때
     */
    open fun <T : Any> onSuccess(data: T) {}

    /**
     * 데이터 처리 시 http 에러 발생 시
     */
    open fun onApiError(e: Result.Failure.Error) {
        Timber.e("onApiError = ${e.code}:${e.message}")
    }

    /**
     * 데이터 처리 시 에러 발생 시
     */
    open fun onException(e: Result.Failure.Exception) {
        Timber.e("onException = ${e.e.message.toString()}")
    }
}