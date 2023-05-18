package com.carles.hyrule.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.carles.hyrule.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ErrorDialogFragment : AppCompatDialogFragment() {

    private val retry by lazy {
        requireArguments().getBoolean(EXTRA_RETRY, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = requireArguments().getString(EXTRA_MESSAGE, getString(R.string.error_server_response))

        val alertDialogBuilder = MaterialAlertDialogBuilder(requireActivity()).setMessage(message)
        if (retry) {
            isCancelable = false
            alertDialogBuilder.setPositiveButton(R.string.error_retry) { _, _ ->
                dismiss()
            }
        }
        return alertDialogBuilder.create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        setFragmentResult(REQUEST_CODE_RETRY, bundleOf(EXTRA_RETRY to retry))
    }

    companion object {
        const val REQUEST_CODE_RETRY = "request_code_retry"

        private const val EXTRA_MESSAGE = "extraMessage"
        const val EXTRA_RETRY = "extraRetry"
    }
}