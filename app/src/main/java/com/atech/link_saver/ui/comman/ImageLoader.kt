package com.atech.link_saver.ui.comman

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.atech.link_saver.R
import com.atech.link_saver.utils.forwardingPainter


@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    @DrawableRes errorImage: Int = R.drawable.img_loading,
    @StringRes contentDes: Int = R.string.img,
    contentScale: ContentScale = ContentScale.Fit,
    onError: () -> Unit = {}
) {

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = contentDes),
        contentScale = contentScale,
        error = forwardingPainter(
            painter = painterResource(id = errorImage),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        ),
        onError = {
            onError()
        }
    )
}