package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.common.TextWithIcon

@Composable
fun InfoList(
    navController: NavController,
    headlineStyle: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    Column {
        InfoItem(
            title = stringResource(id = R.string.terms_and_conditions),
            //description = stringResource(id = R.string.terms_and_conditions_description),
            icon = Screen.ProfileGraph.Terms.icon,
            textStyle = headlineStyle,
            onClick = {
                navController.navigate(Screen.ProfileGraph.Terms.route)
            }
        )
        InfoItem(
            textStyle = headlineStyle,
            title = stringResource(id = R.string.privacy_policy),
            icon = Screen.ProfileGraph.PrivacyPolicy.icon,
            onClick = {
                navController.navigate(Screen.ProfileGraph.PrivacyPolicy.route)
            }
        )
        InfoItem(
            textStyle = headlineStyle,
            title = stringResource(id = R.string.about_us),
            icon = Screen.ProfileGraph.AboutUs.icon,
            onClick = {
                navController.navigate(Screen.ProfileGraph.AboutUs.route)
            }
        )
    }
}


@Composable
fun InfoItem(
    title: String,
    description: String = "",
    icon: ImageVector,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
    ) {
        TextWithIcon(
            text = title,
            prefixIcon = icon,
            prefixIconModifier = Modifier.size(24.dp),
            style = textStyle
        )

        if (description.isNotEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }

}

