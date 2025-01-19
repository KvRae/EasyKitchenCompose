package com.kvrae.easykitchen.presentation.miscellaneous.components


import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomAlertDialogWithContent(
    title: String = "",
    content : @Composable () -> Unit,
    confirmText: String = "",
    dismissText: String = "",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}

){
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,

            ),
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = {},

        text = {
            content()
        },
        title = { Text(text = title) },
        shape = RoundedCornerShape(16.dp),
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            if (dismissText.isNotEmpty())
                TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = dismissText,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
        },
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                RoundedCornerShape(16.dp))
    )
}