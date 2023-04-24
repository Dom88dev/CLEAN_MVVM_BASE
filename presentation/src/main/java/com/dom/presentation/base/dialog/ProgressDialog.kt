package com.dom.presentation.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.dom.presentation.databinding.DialogProgressBinding

class ProgressDialog(
    listener: DialogListener,
    inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> DialogProgressBinding,
    private val isScanning: Boolean = false
) : BaseDialog<DialogProgressBinding, DialogListener>(listener, inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}