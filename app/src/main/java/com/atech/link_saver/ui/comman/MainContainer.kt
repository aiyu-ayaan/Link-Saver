package com.atech.link_saver.ui.comman

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.atech.link_saver.ui.theme.LinkSaverTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues, TopAppBarScrollBehavior) -> Unit = { _, _ -> }
) {
    val scrollState = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = navigationIcon,
                actions = actions,
                scrollBehavior = scrollState
            )
        }
    ) {
        content(it, scrollState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MainContainerPreview() {
    LinkSaverTheme {
        MainContainer(
            title = "Link Saver"
        )
    }
}