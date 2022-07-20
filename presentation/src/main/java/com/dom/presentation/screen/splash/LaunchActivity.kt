package com.dom.presentation.screen.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dom.presentation.base.BaseActivity
import com.dom.presentation.base.delegate.viewBinding
import com.dom.presentation.databinding.ActivityLaunchBinding
import com.dom.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class LaunchActivity : BaseActivity<LaunchViewModel, ActivityLaunchBinding>() {
    override val viewModel: LaunchViewModel by viewModels()
    override val viewBinding: ActivityLaunchBinding by viewBinding(ActivityLaunchBinding::inflate)

    override fun initViews() {
        with(viewBinding) {

        }
    }

    override fun observeData(): Job = lifecycleScope.launchWhenStarted {
        viewModel.isReadyMainScreen.collect {
            if (it) {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                finish()
            }
        }
    }

}