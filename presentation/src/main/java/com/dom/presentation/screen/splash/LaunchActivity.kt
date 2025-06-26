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
import com.dom.presentation.screen.splash.event.LaunchSideEffect
import com.dom.presentation.screen.splash.event.LaunchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchActivity : BaseActivity<LaunchState, LaunchSideEffect, LaunchViewModel, ActivityLaunchBinding>() {
    override val vm: LaunchViewModel by viewModels()
    override val vb: ActivityLaunchBinding by viewBinding(ActivityLaunchBinding::inflate)

    override fun initViews() {
        with(vb) {}
    }

    override fun navigate(from: String) {
        when(from) {
            MainActivity::class.java.name -> {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                finish()
            }
        }
    }

}