package com.atech.link_saver.ui.screens.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atech.link_saver.R
import com.atech.link_saver.ui.comman.BottomAppbar
import com.atech.link_saver.ui.comman.EmptyItemComponent
import com.atech.link_saver.ui.comman.LinkItem
import com.atech.link_saver.ui.comman.MainContainer
import com.atech.link_saver.ui.screens.home.HomeScreenState
import com.atech.link_saver.ui.theme.LinkSaverTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    title: String = "Home",
    state: HomeScreenState,
    navHostController: NavController = rememberNavController()
) {
    val lazyColumnScrollState = rememberLazyListState()
    MainContainer(
        modifier = modifier,
        title = title,
        bottomAppbar = {
            val navBackStackEntry = navHostController.currentBackStackEntryAsState()
            AnimatedVisibility(
                visible = !lazyColumnScrollState.isScrollInProgress,
                enter = slideInVertically { it * 2 },
                exit = slideOutVertically { it * 2 },
            ) {
                BottomAppbar(
                    backStackEntry = navBackStackEntry,
                )
            }
        }
    ) { padding, scrollState ->
        if (state.items.isEmpty()) {
            EmptyItemComponent(
                modifier = Modifier.fillMaxSize(),
                text = stringResource(R.string.press_to_add_link),
            )
            return@MainContainer
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollState.nestedScrollConnection),
            contentPadding = padding,
            state = lazyColumnScrollState
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
