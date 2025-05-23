package com.dom.presentation.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dom.presentation.databinding.DialogProgressBinding

class ProgressDialog : BaseDialog<DialogProgressBinding, DialogListener>() {
    override val inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> DialogProgressBinding
        get() = DialogProgressBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {}
    }
}