package com.dom.presentation.base.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomDialog<T : ViewBinding, L : DialogListener>(
    private val dialogListener: L,
    private val inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> T
) : BottomSheetDialogFragment() {

    protected lateinit var binding: T
        private set

    protected lateinit var listener: L

    protected lateinit var behavior: BottomSheetBehavior<View>

    private var statusBarColor: Int? = null

    @SuppressLint("ClickableViewAccessibility")
    val touchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                behavior.isHideable = false
                view.isPressed = true
            }
            MotionEvent.ACTION_MOVE -> {
                return@OnTouchListener false
            }
            MotionEvent.ACTION_UP -> {
                behavior.isHideable = true
                if (view.isPressed) {
                    view.isPressed = false
                    view.callOnClick()
                }
            }
        }
        true
    }

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

    // 길이가 화면의 절반을 넘어가는 다이얼로그의 경우 STATE_HALF_EXPANDED 상태로 열리는데 완전히 펼쳐진 상태로 열리도록 처리하는 코드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { v ->
                behavior = BottomSheetBehavior.from(v)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
                setBeHaviorBefore()
            }
        }
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 바꾼 스테이터스바 색상을 원래대로 되돌린다.
        statusBarColor?.let {
            activity?.window?.statusBarColor = it
        }
    }

    open fun setBeHaviorBefore() {
        // 혹여나 bottomSheetBehavior 의 초기 값을 변경하려면 override 해서 세팅
    }
}