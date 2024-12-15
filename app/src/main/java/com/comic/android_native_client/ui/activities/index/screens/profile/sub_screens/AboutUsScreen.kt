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
fun AboutUsScreen(
    horizontalPadding: Dp = 18.dp,
    navController: NavController
) {
    BackTopAppBarScreen(
        headerText = {
            Text("About Us")
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
                title = "1. About Us",
                content = "We are a team dedicated to providing an engaging and seamless experience for our users. Our mission is to help you discover, create, and enjoy the best comics."
            )

            TitleContentSection(
                title = "2. Our Story:",
                content = "Our journey began with a passion for comics and a desire to create an app that would bring fans and creators together. Our goal is to offer a platform where people can explore a variety of comic genres, share their creations, and connect with like-minded individuals."
            )

            TitleContentSection(
                title = "3. Our Values:",
                content = "We believe in creativity, community, and inclusivity. We strive to foster a positive and respectful environment for all users of our platform."
            )

            TitleContentSection(
                title = "4. Contact Us:",
                content = "Have questions or feedback? We’d love to hear from you! You can reach us at comicapplication2003@gmail.com."
            )

            TitleContentSection(
                title = "Thank you for being a part of our community, and we hope you enjoy using our app!",
                content = ""
            )
        }
    }
}