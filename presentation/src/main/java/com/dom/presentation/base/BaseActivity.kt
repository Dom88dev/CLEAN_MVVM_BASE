package com.dom.presentation.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.dom.presentation.base.dialog.ProgressDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import com.dom.domain.model.Result
import com.dom.presentation.base.dialog.DialogListener
import com.dom.presentation.databinding.DialogProgressBinding

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding> : AppCompatActivity(), DialogListener {

    abstract val vm: VM
    abstract val vb: VB
    private lateinit var fetchJob: Job
    abstract fun initViews()
    abstract fun observeData(): Job

    protected val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            onResultPermissionRequest(it.values.all { granted -> granted })
        }

    protected val requestNotificationEnabledLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onResultNotificationRequest()
        }

    private var progress: ProgressDialog? = null
    lateinit var backPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initScreen()
    }

    open fun initScreen() {
        //region 뒤로가기 콜백 코드 상속 구현
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedCallback()
            }
        }.also {
            backPressedCallback = it
            onBackPressedDispatcher.addCallback(this, it)
        }
        //endregion
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.spinner.collect {
                        if (it) showProgress()
                        else hideProgress()
                    }
                }
            }
        }
        initViews()
        fetchJob = vm.fetch()
        observeData()
    }

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }

    open fun onBackPressedCallback() {  finish() }

    fun showProgress() {
        // 프로그레싱 표시
        if (progress == null) {
            initProgress().also {
                showDialog(it)
                progress = it
            }
        }
    }

    /**
     * 프로그레스 다이얼로그를 다르게 하고 싶을 때 override
     */
    open fun initProgress(): ProgressDialog {
        return ProgressDialog(this, DialogProgressBinding::inflate)
    }

    fun hideProgress() {
        // 프로그레싱 숨김
        progress?.let {
            it.dismiss()
            progress = null
        }
    }

    fun showDialog(dialog: DialogFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            transaction.remove(prev)
        }
        transaction.addToBackStack(null)
        transaction.commit()
        dialog.show(supportFragmentManager, "dialog")
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
    private fun onLoading() {
        showProgress()
    }

    /**
     * 데이터 처리 결과 성공일 때
     */
    open fun <T : Any> onSuccess(data: T) {
        hideProgress()
    }

    /**
     * 데이터 처리 시 http 에러 발생 시
     */
    open fun onApiError(e: Result.Failure.Error) {
        hideProgress()
        Timber.e("onApiError = ${e.code}:${e.message}")
    }

    /**
     * 데이터 처리 시 에러 발생 시
     */
    open fun onException(e: Result.Failure.Exception) {
        hideProgress()
        Timber.e("onException = ${e.e.message.toString()}")
    }

    /**
     * 필요 시 권한 허용 요청 결과 처리를 오버로드해서 구현
     */
    open fun onResultPermissionRequest(allGranted: Boolean) {}

    /**
     * 필요 시 알림 허용 요청 결과 처리를 오버로드해서 구현
     */
    open fun onResultNotificationRequest() {}
}