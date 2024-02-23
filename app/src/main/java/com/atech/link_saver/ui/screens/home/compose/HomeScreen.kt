package com.atech.link_saver.ui.screens.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.atech.link_saver.ui.screens.home.AddLinkState
import com.atech.link_saver.ui.screens.home.HomeState
import com.atech.link_saver.ui.theme.LinkSaverTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    title: String = "Home",
    state: HomeState,
    navHostController: NavController = rememberNavController()
) {
    val lazyColumnScrollState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    MainContainer(
        modifier = modifier,
        title = title,
        bottomAppbar = {
            val navBackStackEntry = navHostController.currentBackStackEntryAsState()
            AnimatedVisibility(
                visible = !lazyColumnScrollState.isScrollInProgress ,
                enter = slideInVertically { it * 2 },
                exit = slideOutVertically { it * 2 },
            ) {
                BottomAppbar(
                    backStackEntry = navBackStackEntry,
                    onClick = navHostController::navigate,
                    onAddLinkClick = {
                        showBottomSheet = true
                        scope.launch { sheetState.show() }
                    }
                )
            }
        }
    ) { padding, scrollState ->
        if (state.items.isEmpty()) {
            EmptyItemComponent(
                modifier = Modifier.fillMaxSize(),
                text = stringResource(R.string.press_to_add_link),
            )
        } else {
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
        if (showBottomSheet) {
            HomeScreenBottomSheet(
                addLinkState = AddLinkState(),
                sheetState = sheetState,
                scope = scope,
                onDismiss = { showBottomSheet = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreenBottomSheet(
    modifier: Modifier = Modifier,
    addLinkState: AddLinkState,
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
    ) {
        AddLink(
            modifier = modifier,
            state = addLinkState,
            onCancelClick = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    LinkSaverTheme {
        HomeScreen(
            state = HomeState()
        )
    }
}
