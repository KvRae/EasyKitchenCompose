package com.kvrae.easykitchen.ui.components

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kvrae.easykitchen.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = {
            Text(
                text = "EasyKitchen",
                style = AppTypography.titleLarge
            )
        }
    )
}