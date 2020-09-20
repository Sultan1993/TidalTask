package kz.maestrosultan.tidaltask.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter("imageUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    view.load(imageUrl) {
        crossfade(true)
    }
}