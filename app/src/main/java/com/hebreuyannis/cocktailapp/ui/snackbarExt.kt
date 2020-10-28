package com.hebreuyannis.cocktailapp.ui

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.SnackbarMessageManager

fun Fragment.setupSnackbar(
    snackbarMessage: LiveData<SnackbarMessage>,
    snackbarMessageManager: SnackbarMessageManager,
    view: View,
    anchorView: View? = null,
    actionClickListener: (Int) -> Unit = {}
) {

    snackbarMessage.observe(viewLifecycleOwner, { message ->

        val messageText = HtmlCompat.fromHtml(
            requireContext().getString(message.messageId),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        val duration =
            if (message.longDuration) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        val snackbar = Snackbar.make(view, messageText, duration)
        anchorView?.let { snackbar.setAnchorView(R.id.bottom_bar) }
        message.actionId?.let { id ->
            snackbar.setAction(id) {
                actionClickListener(id)
            }
        }
        snackbar.show()
    })

    snackbarMessageManager.observeNextMessage().observe(viewLifecycleOwner, { message ->
        val messageText = HtmlCompat.fromHtml(
            requireContext().getString(message.messageId),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        val duration = if (message.longDuration) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
        val snackbar = Snackbar.make(view, messageText, duration)
        anchorView?.let { snackbar.anchorView = it }
        message.actionId?.let { id ->
            snackbar.setAction(id) {
                actionClickListener(id)
            }
        }
        snackbar.show()
    })

}