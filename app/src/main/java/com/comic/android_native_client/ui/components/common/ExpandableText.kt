package com.comic.android_native_client.ui.components.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,

    text: String,
    textModifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    collapsedMaxLines: Int = 5,

    expandTitle: String = stringResource(R.string.show_more),
    collapseTitle: String = stringResource(R.string.show_less),
    buttonTextColor: Color = MaterialTheme.colorScheme.primary,
) {
    val isExpanded = remember { mutableStateOf(false) }
    val showSeeMore = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .animateContentSize()
    ) {
        Text(
            text = text,
            modifier = textModifier,
            style = style,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            maxLines = if (isExpanded.value) Int.MAX_VALUE else collapsedMaxLines,
            overflow = overflow,
            softWrap = softWrap,
            lineHeight = lineHeight,
            onTextLayout = {
                if (!isExpanded.value) {
                    showSeeMore.value = it.multiParagraph.didExceedMaxLines
                }
            }
        )
        if (showSeeMore.value) {
            TextButton(
                onClick = { isExpanded.value = !isExpanded.value },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    style = style,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    letterSpacing = letterSpacing,
                    textDecoration = textDecoration,
                    textAlign = textAlign,
                    text = if (isExpanded.value) collapseTitle else expandTitle,
                    color = buttonTextColor,

                    )
            }
        }
    }
}
