package com.atech.link_saver.ui.screens.archive.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.atech.link_saver.R
import com.atech.link_saver.ui.comman.EmptyItemComponent
import com.atech.link_saver.ui.comman.LinkItem
import com.atech.link_saver.ui.comman.MainContainer
import com.atech.link_saver.ui.screens.bin.BinState
import com.atech.link_saver.ui.theme.LinkSaverTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArchiveScreen(
    modifier: Modifier = Modifier,
    title: String = "Archive",
    state: BinState = BinState(),
    navHostController: NavController = rememberNavController()
) {
    MainContainer(
        modifier = modifier,
        title = title,
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ) { _, scrollState ->
        if (state.items.isEmpty()) {
            EmptyItemComponent(
                modifier = Modifier.fillMaxSize(),
                text = stringResource(R.string.no_archive),
            )
            return@MainContainer
        }
        LazyColumn(
            modifier = Modifier
                .nestedScroll(scrollState.nestedScrollConnection)
        ) {
            items(state.items, key = { it.link }) { item ->
                LinkItem(model = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArchiveScreenPreview() {
    LinkSaverTheme {
        ArchiveScreen()
    }
}