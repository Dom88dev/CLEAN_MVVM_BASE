package com.dom.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.dom.domain.ISideEffect
import com.dom.domain.IState
import com.dom.domain.IView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseFragment<S : IState, SE : ISideEffect, VM : BaseViewModel<S, *, SE>, VB : ViewBinding> :
    Fragment(),
    IView<S, SE> {
    abstract val vm: VM
    abstract val vb: VB
    private lateinit var fetchJob: Job

    //    lateinit var backPressedCallback: OnBackPressedCallback

    abstract fun initViews()
    private fun observeData(): Job = viewLifecycleOwner.lifecycleScope.launch {
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

    open fun initScreen() {
        //region 뒤로가기 콜백 코드 상속 구현
//        object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                onBackPressedCallback()
//            }
//        }.also {
//            backPressedCallback = it
//            requireActivity().onBackPressedDispatcher.addCallback(this.viewLifecycleOwner, it)
//        }
        //endregion
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

//    open fun onBackPressedCallback() {
//        if(requireActivity() is BaseActivity<*, *>) {
//            (requireActivity() as BaseActivity<*, *>).backPressedCallback.handleOnBackPressed()
//        }
//    }

    fun showDialog(
        dialog: DialogFragment,
        tag: String = "dialog",
        onDismiss: (() -> Unit)? = null
    ) {
        requireActivity().supportFragmentManager.commit {
            requireActivity().supportFragmentManager.findFragmentByTag(tag)?.also {
                remove(it)
            }
        }
        dialog.show(requireActivity().supportFragmentManager, tag)
        onDismiss?.also { callback ->
            requireActivity().supportFragmentManager.executePendingTransactions()
            dialog.dialog?.setOnDismissListener {
                callback.invoke()
            }
        }
    }

}