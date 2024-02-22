package com.atech.link_saver.ui.comman

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atech.core.room.link.LinkModel
import com.atech.link_saver.ui.theme.LinkSaverTheme
import com.atech.link_saver.ui.theme.grid_1

@Composable
fun LinkItem(
    modifier: Modifier = Modifier,
    model: LinkModel,
    onEditClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = grid_1, horizontal = grid_1)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(grid_1),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Start
        ) {
            AnimatedVisibility(visible = model.fetchIcon != null && model.isFetchDone) {
                model.fetchIcon?.let {
                    ImageLoader(
                        imageUrl = it,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
            AnimatedVisibility(visible = !model.isFetchDone) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.padding(end = grid_1),
                    strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
                )
            }
            Text(
                text = model.link,
                modifier = Modifier
                    .padding(grid_1)
                    .weight(.9f),
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(
                onClick = onEditClick,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(grid_1)
                .padding(bottom = grid_1),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.Start
        ) {
            AnimatedVisibility(visible = model.fetchTitle != null) {
                HorizontalDivider()
                Text(
                    modifier = Modifier.padding(top = grid_1),
                    text = model.fetchTitle ?: "",
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            AnimatedVisibility(visible = model.fetchDes != null) {
                Text(
                    text = model.fetchTitle ?: "",
                    maxLines = 3,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            AnimatedVisibility(visible = model.fetchImage != null) {
                ImageLoader(
                    imageUrl = model.fetchImage ?: "",
                    modifier = Modifier
                        .padding(top = grid_1)
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(grid_1)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LinkItemPreview() {
    LinkSaverTheme {
        LinkItem(
            model = LinkModel(
                link = "https://www.google.com",
                sortDes = "Google",
                fetchIcon = "https://www.google.com/favicon.ico",
                fetchTitle = "Google",
                fetchDes = "Google",
                isFetchDone = false,
            )
        )
    }
}