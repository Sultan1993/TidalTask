package kz.maestrosultan.tidaltask.presentation.albums

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.palette.graphics.Palette
import coil.bitmap.BitmapPool
import coil.load
import coil.size.Size
import coil.transform.Transformation
import kotlinx.android.synthetic.main.view_album_header.view.*
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.getColorCompat
import kz.maestrosultan.tidaltask.domain.entity.Album

class AlbumHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_album_header, this)
        setBackgroundColor(context.getColorCompat(R.color.colorPrimary))
    }

    fun configure(album: Album) {
        albumTitleLabel.text = album.title
        albumArtistLabel.text = album.artist?.name
        albumCover.load(album.coverLarge) {
            transformations(object : Transformation {

                override fun key() = "paletteTransformer${System.currentTimeMillis()}"

                override suspend fun transform(
                    pool: BitmapPool,
                    input: Bitmap,
                    size: Size
                ): Bitmap {
                    configureGradient(input)
                    return input
                }
            })
        }
    }

    private fun configureGradient(input: Bitmap) {
        val palette = Palette.from(input).generate()
        palette.getDarkVibrantColor(0)

        val swatch = palette.vibrantSwatch

        swatch?.let {
            val drawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(it.rgb, R.color.colorPrimary)
            )
            background = drawable
        }
    }
}