/*
 *  Created by aiyu
 *  Copyright (c) 2021 . All rights reserved.
 *  BIT App
 *
 */

package com.atech.link_saver.ui.comman

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.atech.link_saver.ui.theme.LinkSaverTheme
import kotlinx.coroutines.android.awaitFrame


@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit = {},
    clearIconClick: () -> Unit = {},
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusRequester: FocusRequester? = null,
    enable: Boolean = true,
    colors: TextFieldColors = textFieldColors(),
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = {
        if (value.isNotBlank()) Icon(imageVector = Icons.Outlined.Clear,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                clearIconClick()
            })
    },
    supportingText: @Composable (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester?.requestFocus()
    }

    OutlinedTextField(
        modifier = modifier.let {
            if (focusRequester == null) it
            else it.focusRequester(focusRequester)
        },
        value = value,
        maxLines = maxLines,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            leadingIcon?.invoke()
        },
        trailingIcon = trailingIcon,
        colors = colors,
        isError = isError,
        keyboardOptions = keyboardOptions,
        enabled = enable,
        readOnly = readOnly,
        interactionSource = interactionSource,
        supportingText = supportingText,
    )
}

@Composable
fun MutableInteractionSource.clickable(
    action: () -> Unit
) = this.also { interactionSource ->
    LaunchedEffect(key1 = interactionSource) {
        interactionSource.interactions.collect {
            if (it is PressInteraction.Release) {
                action()
            }
        }
    }

}


@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    cursorColor = MaterialTheme.colorScheme.primary,
    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
    unfocusedContainerColor = MaterialTheme.colorScheme.surface
)

@Preview(
    showBackground = true
)
@Preview(
    showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Preview(
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE
)
@Preview(
    showBackground = false, wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Preview(
    showBackground = false, wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE
)
@Composable
fun EditTextPreview() {
    LinkSaverTheme {
        EditText(
            modifier = Modifier.fillMaxWidth(),
            value = "", placeholder = "Subject Name",
        )
    }
}
