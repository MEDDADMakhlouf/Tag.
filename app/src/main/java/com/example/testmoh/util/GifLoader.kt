package com.example.testmoh.util

import android.graphics.drawable.Drawable // Can be removed if not used elsewhere
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.decode.GifDecoder
import coil.drawable.MovieDrawable.Companion.REPEAT_INFINITE
import coil.request.ImageRequest
import coil.request.repeatCount // This import is for the extension function
//import coil.request.REPEAT_INFINITE // THIS IS THE CORRECT COIL CONSTANT FOR INFINITE LOOPING

@Composable
fun GifImage(
    @DrawableRes drawableRes: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val imageRequest = ImageRequest.Builder(context)
        .data(drawableRes)
        .crossfade(true)
        .apply {
            // This is the key line. We must use the REPEAT_INFINITE from coil.request
            repeatCount(REPEAT_INFINITE)
        }
        .build()

    val painter = rememberAsyncImagePainter(
        model = imageRequest,
        imageLoader = imageLoader
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = contentScale
    )
}
