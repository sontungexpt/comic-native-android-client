package com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.ui.components.common.TitleContentSection
import com.comic.android_native_client.ui.components.layout.BackTopAppBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(
    horizontalPadding: Dp = 18.dp,
    navController: NavController
) {
    BackTopAppBarScreen(
        headerText = {
            Text("Privacy Policy")
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
                title = "1. Information We Collect",
                content = "We collect personal information such as your name, email address, and usage data when you register and use our app."
            )

            TitleContentSection(
                title = "2. How We Use Your Information",
                content = "The information we collect is used to personalize your experience, improve our app, and send you relevant notifications or updates."
            )

            TitleContentSection(
                title = "3. Data Security",
                content = "We take reasonable steps to protect your personal information from unauthorized access, alteration, or destruction."
            )

            TitleContentSection(
                title = "4. Sharing Your Information:",
                content = "We do not sell, trade, or rent your personal information to third parties. However, we may share your information with trusted service providers who assist us in running the app."
            )
            TitleContentSection(
                title = "5. Your Rights:",
                content = "You have the right to access, update, or delete your personal information. You can also request to opt-out of marketing communications."
            )
            TitleContentSection(
                title = "6. Changes to This Policy:",
                content = "We may update this Privacy Policy from time to time. Any changes will be posted here, and the effective date will be updated accordingly."
            )
        }
    }
}
