package com.atech.link_saver.ui.screens.home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atech.link_saver.ui.comman.EditText
import com.atech.link_saver.ui.screens.home.AddLinkEvents
import com.atech.link_saver.ui.screens.home.AddLinkState
import com.atech.link_saver.ui.theme.LinkSaverTheme
import com.atech.link_saver.ui.theme.grid_1

@Composable
internal fun AddLink(
    modifier: Modifier = Modifier,
    state: AddLinkState = AddLinkState(),
    onEvent: (AddLinkEvents) -> Unit = {},
    discussionRequest: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(grid_1),
        verticalArrangement = Arrangement.Center,
    ) {
        EditText(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = grid_1, top = grid_1),
            value = state.link,
            placeholder = "Enter Link",
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Link, contentDescription = null)
            },
            supportingText = {
                Text(
                    text = if (state.isLinkError) "Link is not valid"
                    else "Required",
                )
            },
            isError = state.isLinkError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                onEvent(AddLinkEvents.OnLinkChange(it))
            },
            clearIconClick = {
                onEvent(AddLinkEvents.OnLinkChange(""))
            }
        )
        EditText(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = grid_1),
            value = state.shortDes,
            placeholder = "Enter Short Description",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Description, contentDescription = null)
            },
            onValueChange = {
                onEvent(AddLinkEvents.OnShortDesChange(it))
            },
            clearIconClick = {
                onEvent(AddLinkEvents.OnShortDesChange(""))
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    onEvent(AddLinkEvents.OnCancelClick)
                    discussionRequest()
                }, modifier = Modifier
                    .padding(end = grid_1)
                    .weight(.5f)

            ) {
                Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null)
                Text(
                    text = "Cancel", modifier = Modifier.padding(start = grid_1)
                )
            }
            TextButton(
                onClick = {
                    onEvent(AddLinkEvents.OnSaveClick)
                    discussionRequest.invoke()
                }, modifier = Modifier
                    .padding(end = grid_1)
                    .weight(.5f)
            ) {
                Icon(imageVector = Icons.Outlined.Save, contentDescription = null)
                Text(
                    text = "Save", modifier = Modifier.padding(start = grid_1)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddLinkPreview() {
    LinkSaverTheme {
        AddLink()
    }
}