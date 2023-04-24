package com.dom.presentation.base.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<T : ViewBinding, L : DialogListener>(
    private val dialogListener: L,
    private val inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> T
) : AppCompatDialogFragment() {

    protected lateinit var binding: T
        private set

    protected lateinit var listener: L

    private var statusBarColor: Int? = null


    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddPatientDialogListener so we can send events to the host
            listener = dialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement DialogAlertTaskListener")
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        // 뷰를 생성할 때 스테이터스바 색상을 바꿔주며 기존 색상을 저장해둔다.
        activity?.window?.let {
            statusBarColor = it.statusBarColor
            //todo 색상 id 변경
            it.statusBarColor = resources.getColor(android.R.color.transparent, null)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 바꾼 스테이터스바 색상을 원래대로 되돌린다.
        statusBarColor?.let {
            activity?.window?.statusBarColor = it
        }
    }
}