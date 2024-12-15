package com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.comic.android_native_client.ui.components.common.TitleContentSection
import com.comic.android_native_client.ui.components.layout.BackTopAppBarScreen
import com.comic.shareable_theme.ui.theme.ShareableTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsScreen(
    navController: NavHostController,
    horizontalPadding: Dp = 18.dp
) {
    BackTopAppBarScreen(
        headerText = {
            Text("Terms and Conditions")
        },
        onBack = {
            navController.popBackStack()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = horizontalPadding)
        ) {
            TitleContentSection(
                title = "Welcome to our app!",
                content = "By accessing or using this app, you agree to comply with the following terms and conditions:"
            )

            TitleContentSection(
                title = "1. User Accounts:",
                content = "You must create an account to use some of the features of the app. You are responsible for maintaining the confidentiality of your account information."
            )

            TitleContentSection(
                title = "2. Acceptable Use:",
                content = "You agree to use this app only for lawful purposes and in a way that does not violate the rights of others or restrict their use of the app."
            )

            TitleContentSection(
                title = "3. Termination:",
                content = "We reserve the right to terminate your access to the app if we determine that you have violated any of these terms."
            )

            TitleContentSection(
                title = "4. Limitation of Liability:",
                content = "We are not liable for any loss or damage arising from the use of this app, whether direct or indirect."
            )

            TitleContentSection(
                title = "5. Changes to the Terms:",
                content = "We may update these terms from time to time. Any changes will be posted in this section, and your continued use of the app will constitute acceptance of the updated terms."
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TermsScreenPreview() {
    ShareableTheme {
        TermsScreen(
            navController = NavHostController(LocalContext.current),
        )
    }
}
