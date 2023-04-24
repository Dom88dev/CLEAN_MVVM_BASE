package com.dom.presentation.base.snackbar

import com.dom.presentation.base.dialog.DialogListener

interface SnackBarListener : DialogListener {}

interface OneButtonListener : SnackBarListener {
    fun onClick()
}

interface TwoButtonListener : SnackBarListener {
    fun onClick(positive: Boolean)
}

interface MultiButtonListener : SnackBarListener {
    fun onClick(position: Int)
}