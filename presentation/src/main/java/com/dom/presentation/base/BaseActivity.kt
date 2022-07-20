package com.dom.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding> : AppCompatActivity() {

    abstract val viewModel: VM
    abstract val viewBinding: VB
    private lateinit var fetchJob: Job
    abstract fun initViews()
    abstract fun observeData(): Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initState()
    }

    open fun initState() {
        initViews()
        fetchJob = viewModel.fetch()
        observeData()
    }

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }
}