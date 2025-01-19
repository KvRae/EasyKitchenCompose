package com.kvrae.easykitchen.presentation.miscellaneous.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NoDataScreen(
    modifier: Modifier = Modifier,
    message: String = "Content Not Available",
    icon: ImageVector = Icons.Rounded.Close,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .size(128.dp),
            imageVector = icon,
            contentDescription = "No Data found Icon",
        )
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
