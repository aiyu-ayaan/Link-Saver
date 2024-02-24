package com.atech.link_saver.ui.screens.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.atech.core.room.link.LinkModel
import com.atech.link_saver.R
import com.atech.link_saver.ui.comman.BottomAppbar
import com.atech.link_saver.ui.comman.EmptyItemComponent
import com.atech.link_saver.ui.comman.LinkItem
import com.atech.link_saver.ui.comman.MainContainer
import com.atech.link_saver.ui.screens.home.AddLinkEvents
import com.atech.link_saver.ui.screens.home.AddLinkState
import com.atech.link_saver.ui.theme.LinkSaverTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    title: String = "Home",
    pagingLinkFlow: Flow<PagingData<LinkModel>>,
    addLinkState: AddLinkState,
    onAddLinkEvent: (AddLinkEvents) -> Unit = {},
    navHostController: NavController = rememberNavController()
) {
    val lazyColumnScrollState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var link = pagingLinkFlow.collectAsLazyPagingItems()
    LaunchedEffect(key1 = pagingLinkFlow) {

    }
    var showBottomSheet by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = sheetState.isVisible) {
        onAddLinkEvent.invoke(AddLinkEvents.OnClearRequest)
    }
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
                    onClick = navHostController::navigate,
                    onAddLinkClick = {
                        showBottomSheet = true
                        scope.launch { sheetState.show() }
                    }
                )
            }
        }
    ) { padding, scrollState ->
        if (link.itemCount == 0) {
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
                items(
                    count = link.itemCount,
                    key = link.itemKey { model -> model.link },
                    contentType = link.itemKey { model -> model.link }
                ) {
                    link[it]?.let { item ->
                        LinkItem(model = item)
                    }
                }
            }
        }
        if (showBottomSheet) {
            HomeScreenBottomSheet(
                addLinkState = addLinkState,
                sheetState = sheetState,
                scope = scope,
                onEvent = onAddLinkEvent,
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
    onEvent: (AddLinkEvents) -> Unit = {},
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
    ) {
        AddLink(
            modifier = modifier,
            state = addLinkState,
            onEvent = onEvent,
            discussionRequest = {
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
            addLinkState = AddLinkState(),
            pagingLinkFlow = emptyFlow()
        )
    }
}
