package com.comic.android_native_client.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.comic.android_native_client.ui.globalState.RANDOM_AVATAR


/**
 * Composable function to display an avatar image with optional loading, success, and error states.
 * This function supports loading images from URLs, with fallback options for placeholders
 * and error images.
 *
 * @param imageUrl The URL of the image to load. If null, the component will not attempt to load an image.
 * @param contentDescription A description of the image for accessibility purposes.
 * @param modifier The modifier to be applied to the avatar, allowing customization of size, padding, etc.
 * @param placeholder An optional placeholder image displayed while the image is loading. Only works if [imageUrl] is not null.
 * @param fallback An optional fallback image displayed if the image fails to load. Only works if [imageUrl] is not null.
 * @param onLoading A callback triggered while the image is in the loading state. Only works if [imageUrl] is not null.
 * @param onSuccess A callback triggered when the image loads successfully. Only works if [imageUrl] is not null.
 * @param onError A callback triggered when there is an error loading the image. Only works if [imageUrl] is not null.
 * @param contentScale Determines how the image content should be scaled inside the container. Defaults to [ContentScale.Crop].
 * @param alignment Specifies how the image content should be aligned within the container. Defaults to [Alignment.Center].
 * @param colorFilter An optional color filter to apply to the image.
 * @param alpha Specifies the opacity of the image, with 1.0 being fully opaque and 0.0 fully transparent. Defaults to [DefaultAlpha].
 * @param filterQuality The quality of the image filter used when scaling. Only works if [imageUrl] is not null.
 * @param clipToBounds If true, the image will be clipped to the bounds of its container. Only works if [imageUrl] is not null. Defaults to true.
 *
 * Example usage:
 * ```
 * Avatar(
 *     imageUrl = "https://example.com/avatar.jpg",
 *     contentDescription = "User avatar",
 *     placeholder = painterResource(R.drawable.placeholder),
 *     fallback = painterResource(R.drawable.fallback),
 *     onLoading = { /* Handle loading state */ },
 *     onSuccess = { /* Handle success state */ },
 *     onError = { /* Handle error state */ },
 *     modifier = Modifier.size(48.dp)
 * )
 * ```
 */
@Composable
fun Avatar(
    imageUrl: String? = null,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    // only works if imageUrl is not null
    placeholder: Painter? = null,
    // only works if imageUrl is not null
    fallback: Painter? = null,
    // only works if imageUrl is not null
    onLoading: AsyncImagePainter.State.Loading.() -> Unit = {},
    // only works if imageUrl is not null
    onSuccess: AsyncImagePainter.State.Success.() -> Unit = {},
    // only works if imageUrl is not null
    onError: AsyncImagePainter.State.Error.() -> Unit = {},
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    colorFilter: ColorFilter? = null,
    alpha: Float = DefaultAlpha,
    // only works if imageUrl is not null
    filterQuality: FilterQuality = DefaultFilterQuality,
    // only works if imageUrl is not null
    clipToBounds: Boolean = true,
) {

    AsyncImage(
        model = imageUrl,
        error = painterResource(RANDOM_AVATAR),
        fallback = fallback ?: painterResource(RANDOM_AVATAR),
        placeholder = placeholder,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = onError,
        alignment = alignment,
        colorFilter = colorFilter,
        alpha = alpha,
        filterQuality = filterQuality,
        clipToBounds = clipToBounds

    )

}

@Preview
@Composable
fun AvatarPreview() {
    Avatar(imageUrl = "")
}