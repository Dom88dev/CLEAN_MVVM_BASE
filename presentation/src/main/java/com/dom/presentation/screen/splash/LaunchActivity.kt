package com.dom.presentation.screen.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dom.presentation.base.BaseActivity
import com.dom.presentation.base.delegate.viewBinding
import com.dom.presentation.databinding.ActivityLaunchBinding
import com.dom.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchActivity : BaseActivity<LaunchViewModel, ActivityLaunchBinding>() {
    override val vm: LaunchViewModel by viewModels()
    override val vb: ActivityLaunchBinding by viewBinding(ActivityLaunchBinding::inflate)

    override fun initViews() {
        with(vb) {

        }
    }

    override fun observeData(): Job = lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                vm.isReadyMainScreen.collect {
                    if (it) {
                        startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

}