package com.dom.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.dom.domain.ISideEffect
import com.dom.domain.IState
import com.dom.domain.IView
import com.dom.presentation.base.dialog.DialogListener
import com.dom.presentation.base.dialog.ProgressDialog
import com.dom.presentation.util.Extensions.createNotificationChannels
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseActivity<S : IState, SE : ISideEffect, VM : BaseViewModel<S, *, SE>, VB : ViewBinding> :
    AppCompatActivity(), IView<S, SE>, DialogListener {

    abstract val vm: VM
    abstract val vb: VB
    private lateinit var fetchJob: Job
    abstract fun initViews()
    private fun observeData(): Job = lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                vm.state.collect {
                    render(it)
                }
            }
            launch {
                vm.navigation.collect {
                    navigate(it)
                }
            }
        }
    }

    //shared preferences 를 사용할 경우 주석 해제
//    lateinit var prefs: SharedPreferenceManager

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
        //shared preferences 를 사용할 경우 주석 해제
//        prefs = SharedPreferenceManager(applicationContext)
        createNotificationChannels()
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

    open fun onBackPressedCallback() {
        finish()
    }

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
        return ProgressDialog()
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


    override fun render(state: S) {
        if (state.isLoading) showProgress() else hideProgress()
        if (state.errorMessage.isNullOrBlank().not()) Toast.makeText(
            this,
            state.errorMessage,
            Toast.LENGTH_SHORT
        ).show()
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