package com.dom.presentation.base.snackbar

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar

abstract class CustomSnackBar<T : ViewBinding, L : SnackBarListener>(
    viewBindingFactory: (LayoutInflater) -> T,
    view: View,
    private val listenr: L,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    private val context = view.context
    private val snackbar = Snackbar.make(view, "", length)
    private val binding = viewBindingFactory.invoke(LayoutInflater.from(context))
    private var isShowing = false


    init {
        initViews()
    }

    private fun initViews() {
        with(snackbar.view as Snackbar.SnackbarLayout) {
            removeAllViews()
            setPadding(0, 0, 0, 0)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root)
            initBinding(binding)
            snackbar.animationMode = ANIMATION_MODE_SLIDE
        }
    }

    fun show() {
        snackbar.show()
        isShowing = true
    }

    fun dismiss() {
        snackbar.dismiss()
        isShowing = false
    }

    fun setDismissListener(listener : () -> Unit) {
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                listener.invoke()
            }
        })
    }

    fun isShowing() = isShowing

    abstract fun initBinding(binding: T)

    companion object {
        enum class SnackBarKind {
            PermissionInfo, UpdateRequest, UpdateForce, PhotoChooser, LicenseChooser
        }
    }
}