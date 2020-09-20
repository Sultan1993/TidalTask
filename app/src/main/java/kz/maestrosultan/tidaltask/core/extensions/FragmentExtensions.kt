package kz.maestrosultan.tidaltask.core.extensions

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kz.maestrosultan.tidaltask.R

fun Fragment.navigate(direction: NavDirections) {
    try {
        findNavController().navigate(direction)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

open class SnackbarAction(@StringRes val title:  Int, val action: () -> Unit)
class RetryAction(action: () -> Unit): SnackbarAction(R.string.retry, action)

fun Fragment.showSnackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, action: SnackbarAction? = null) {
    view?.showSnackbar(text, duration, action)
}

fun Fragment.showSnackbar(@StringRes text: Int, duration: Int = Snackbar.LENGTH_LONG, action: SnackbarAction? = null) {
    view?.showSnackbar(text, duration, action)
}

fun View.showSnackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, action: SnackbarAction? = null) {
    showSnackBarInternal({ view -> Snackbar.make(view, text, duration) }, action)
}

fun View.showSnackbar(@StringRes text: Int, duration: Int = Snackbar.LENGTH_LONG, action: SnackbarAction? = null) {
    showSnackBarInternal({ view -> Snackbar.make(view, text, duration) }, action)
}

private inline fun View.showSnackBarInternal(snackbarBuilder: (View) -> Snackbar, action: SnackbarAction? = null) {
    val snackbar = snackbarBuilder(this)

    snackbar.view.apply {
        findViewById<TextView>(R.id.snackbar_text).apply {
            maxLines = 5
            gravity = Gravity.CENTER_VERTICAL
        }

        ViewCompat.setElevation(this, 6f)

        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(32.dpToPx, 0.dpToPx, 32.dpToPx, 32.dpToPx)
        layoutParams = params
        background = context.getDrawableCompat(R.drawable.bg_snackbar)
    }

    if (action != null) {
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setAction(action.title) { action.action() }
    }

    snackbar.show()
}