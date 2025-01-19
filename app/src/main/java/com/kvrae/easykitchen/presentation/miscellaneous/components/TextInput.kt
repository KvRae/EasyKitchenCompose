package com.kvrae.easykitchen.presentation.miscellaneous.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kvrae.easykitchen.R

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.empty_string),
    label: String = stringResource(R.string.empty_string),
    value: String = stringResource(R.string.empty_string),
    onValueChange: (String) -> Unit = {},
    singleLine: Boolean = true,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    trailingIcon: ImageVector? = null ,
    leadingIcon: ImageVector? = null,
    supportingText: @Composable () -> Unit = {},
    isError: Boolean = false,
    errorText: String = stringResource(R.string.empty_string),
    readOnly: Boolean = false,
    enabled: Boolean = true,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .padding(8.dp),
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            trailingIcon = {
                if (trailingIcon != null)
                Icon(
                    imageVector = trailingIcon ,
                    contentDescription = stringResource(R.string.text_field_icon)
                )
            },
            leadingIcon = {
                if (leadingIcon != null)
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = stringResource(R.string.text_field_icon)
                )
            },
            supportingText = supportingText,
            isError = isError,
            readOnly = readOnly,
            enabled = enabled,
        )
        if (isError) ErrorTextInput(errorText = errorText)
    }
}

@Composable
fun ErrorTextInput(
    modifier: Modifier = Modifier,
    errorText: String = stringResource(R.string.empty_string),

    ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Rounded.Warning, contentDescription = stringResource(R.string.warning_icon) )
        Text(text = errorText)
    }
}