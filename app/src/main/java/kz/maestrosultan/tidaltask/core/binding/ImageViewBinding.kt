package kz.maestrosultan.tidaltask.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter(value = ["imageUrl", "circle"], requireAll = false)
fun bindImageFromUrl(view: ImageView, imageUrl: String?, circle: Boolean = false) {
    view.load(imageUrl) {
        crossfade(true)

        if (circle) {
            transformations(CircleCropTransformation())
        }
    }
}