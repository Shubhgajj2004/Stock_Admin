package com.papercanary.bhimanistockadmin.dashboard.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutlineInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String,
    isNumber: Boolean? = null
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = label)
        },
        keyboardOptions = if (isNumber == true) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions()
        },
        isError = errorMessage.isNotEmpty(),
        supportingText = {
            Text(text = errorMessage)
        }
    )
}