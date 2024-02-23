package com.atech.link_saver.ui.comman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.atech.link_saver.R
import com.atech.link_saver.ui.theme.LinkSaverTheme
import com.atech.link_saver.ui.theme.grid_1

@Composable
fun EmptyItemComponent(
    modifier: Modifier = Modifier,
    text: String = "No Data Found",
    painter: Painter = painterResource(id = R.drawable.img_empty)
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(.5f),
            painter = painter,
            contentDescription = null
        )
        Text(
            text = text,
            modifier = Modifier.padding(grid_1),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyItemComponentPreview() {
    LinkSaverTheme {
        EmptyItemComponent()
    }
}