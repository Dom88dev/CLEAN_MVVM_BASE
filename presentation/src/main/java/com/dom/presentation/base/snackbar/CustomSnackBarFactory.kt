package com.dom.presentation.base.snackbar

import android.view.View

interface CustomSnackBarFactory {
    fun make(
        view: View,
        kind: CustomSnackBar.Companion.SnackBarKind,
        callback: SnackBarListener
    ): CustomSnackBar<*, *>
}