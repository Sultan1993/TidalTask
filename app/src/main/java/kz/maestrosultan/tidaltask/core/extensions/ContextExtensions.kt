package kz.maestrosultan.tidaltask.core.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(resId: Int) = ContextCompat.getDrawable(this, resId)

fun Context.getColorCompat(resId: Int) = ContextCompat.getColor(this, resId)