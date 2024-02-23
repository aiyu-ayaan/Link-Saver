package com.atech.link_saver.ui.screens.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.atech.link_saver.R
import com.atech.link_saver.ui.comman.LinkItem
import com.atech.link_saver.ui.comman.MainContainer
import com.atech.link_saver.ui.screens.home.HomeScreenState
import com.atech.link_saver.ui.theme.LinkSaverTheme
import com.atech.link_saver.ui.theme.grid_4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    title: String = "Home",
    state: HomeScreenState
) {
    MainContainer(
        modifier = modifier,
        title = title
    ) { padding, scrollState ->
        if (state.items.isEmpty()) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .padding(grid_4),
                painter = painterResource(id = R.drawable.img_empty),
                contentDescription = null
            )
            return@MainContainer
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .nestedScroll(scrollState.nestedScrollConnection),
            contentPadding = padding
        ) {
            items(state.items, key = { it.link }) { item ->
                LinkItem(model = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    LinkSaverTheme {
        HomeScreen(
            state = HomeScreenState()
        )
    }
}
