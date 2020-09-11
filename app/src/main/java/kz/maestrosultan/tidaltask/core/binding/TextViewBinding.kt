package kz.maestrosultan.tidaltask.core.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun setTextInt(view: TextView, value: Int) {
    view.text = value.toString()
}