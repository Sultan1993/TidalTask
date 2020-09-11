package kz.maestrosultan.tidaltask.core.extensions

import android.animation.Animator
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter

fun View?.hideKeyboard() {
    val imm = this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        ?: return
    clearFocus()
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View?.showKeyboard() {
    val imm = this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        ?: return
    requestFocus()
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
}

fun View.hideAnimated(hide: Boolean) {
    animate().apply {
        if (hide) {
            alpha(0f)
        } else {
            alpha(1f)
        }

        duration = 300

        setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) { }

            override fun onAnimationEnd(animation: Animator?) {
                visibility = if (hide) {
                    View.INVISIBLE
                } else {
                    View.VISIBLE
                }
            }

            override fun onAnimationCancel(animation: Animator?) = onAnimationEnd(animation)

            override fun onAnimationStart(animation: Animator?) { }
        })
    }
}

@BindingAdapter("isGoneAnimated")
fun bindIsGoneAnimated(view: View, isGone: Boolean) {
    view.hideAnimated(isGone)
}