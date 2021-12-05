package co.com.ceiba.mobile.pruebadeingreso.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import co.com.ceiba.mobile.pruebadeingreso.R

object DialogUtils {

    fun createLoadingDialog(
        context: Context?,
        cancelable: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        return AlertDialog.Builder(context)
            .setView(R.layout.dialog_loading)
            .create().apply {
                setCancelable(cancelable)
            }
    }

    fun showLoadingDialog(
        context: Context?,
        cancelable: Boolean = false,
    ): AlertDialog? {
        if (context == null) return null
        val dialog: AlertDialog? = createLoadingDialog(context, cancelable)
        dialog?.show()
        return dialog
    }
}