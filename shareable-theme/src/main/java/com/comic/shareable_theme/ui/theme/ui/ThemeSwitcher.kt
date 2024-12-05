//package com.comic.shareable_theme.ui.theme.ui
//
//import android.content.Context
//import androidx.compose.foundation.layout.Row
//import androidx.compose.material3.Switch
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import com.comic.shareable_theme.ui.theme.datastore.ThemeDataStore
//import kotlinx.coroutines.launch
//
//@Composable
//fun ThemeSwitcher(context: Context) {
//    val scope = rememberCoroutineScope()
//    val isDarkTheme = ThemeDataStore.getTheme(context).collectAsState(initial = false).value
//
//    val checkedState = remember { mutableStateOf(isDarkTheme) }
//
//    LaunchedEffect(isDarkTheme) {
//        checkedState.value = isDarkTheme
//    }
//
//    Row {
//        Text(text = "Dark Theme")
//
//        Switch(
//            checked = checkedState.value,
//            onCheckedChange = { isChecked ->
//                checkedState.value = isChecked
//                scope.launch {
//                    ThemeDataStore.saveTheme(context, isChecked)
//                }
//            }
//        )
//    }
//}