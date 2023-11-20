package com.dom.presentation.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.dom.presentation.R
import com.dom.presentation.databinding.DialogProgressBinding

class ProgressDialog(
    listener: DialogListener,
    inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> DialogProgressBinding
) : BaseDialog<DialogProgressBinding, DialogListener>(listener, inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {}
    }

    override fun onStart() {
        super.onStart()
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ProgressDialogTheme)
    }
}