package com.atech.link_saver.ui.activity.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.atech.core.room.link.LinkModel
import com.atech.link_saver.ui.screens.archive.compose.ArchiveScreen
import com.atech.link_saver.ui.screens.bin.BinState
import com.atech.link_saver.ui.screens.bin.compose.BinScreen
import com.atech.link_saver.ui.screens.home.HomeViewModel
import com.atech.link_saver.ui.screens.home.compose.HomeScreen
import com.atech.link_saver.utils.animatedComposable
import com.atech.link_saver.utils.sharedViewModel

sealed class NavRoutes(val route: String) {
    data object HomeScreen : NavRoutes("home_screen")
    data object AddLinkScreen : NavRoutes("add_link_screen")

    data object LinkDetailScreen : NavRoutes("link_detail_screen")

    data object ArchiveScreen : NavRoutes("archive_screen")

    data object BinScreen : NavRoutes("bin_screen")
}

val list = listOf(
    LinkModel(
        link = "https://www.google.com",
        sortDes = "Google",
    ),
    LinkModel(
        link = "https://www.facebook.com",
        sortDes = "Facebook",
    ),
    LinkModel(
        link = "https://www.instagram.com",
        sortDes = "Instagram",
    ),
    LinkModel(
        link = "https://www.twitter.com",
        sortDes = "Twitter",
    ),
    LinkModel(
        link = "https://www.linkedin.com",
        sortDes = "Linkedin",
    ),
    LinkModel(
        link = "https://www.youtube.com",
        sortDes = "Youtube",
    ),
    LinkModel(
        link = "https://www.github.com",
        sortDes = "Github",
    ),
    LinkModel(
        link = "https://www.medium.com",
        sortDes = "Medium",
    ),
    LinkModel(
        link = "https://www.tiktok.com",
        sortDes = "Tiktok",
    ),
    LinkModel(
        link = "https://www.whatsapp.com",
        sortDes = "Whatsapp",
    ),
    LinkModel(
        link = "https://www.snapchat.com",
        sortDes = "Snapchat",
    ),
    LinkModel(
        link = "https://www.pinterest.com",
        sortDes = "Pinterest",
    ),
    LinkModel(
        link = "https://www.tumblr.com",
        sortDes = "Tumblr",
    ),
    LinkModel(
        link = "https://www.reddit.com",
        sortDes = "Reddit",
    ),
    LinkModel(
        link = "https://www.quora.com",
        sortDes = "Quora",
    ),
    LinkModel(
        link = "https://www.stackoverflow.com",
        sortDes = "Stackoverflow",
    ),
)

@Composable
internal fun MainNavigation(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavRoutes.HomeScreen.route
    ) {
        animatedComposable(
            route = NavRoutes.HomeScreen.route,
        ) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navHostController)
            HomeScreen(
                state = viewModel.homeState.value,
                navHostController = navHostController,
                addLinkState = viewModel.addLinkState.value,
                onAddLinkEvent = viewModel::onAddLinkEvent,
            )
        }
        animatedComposable(
            route = NavRoutes.ArchiveScreen.route,
        ) {
            ArchiveScreen(
                navHostController = navHostController,
                state = BinState()
            )
        }
        animatedComposable(
            route = NavRoutes.BinScreen.route,
        ) {
            BinScreen(
                navHostController = navHostController,
                state = BinState()
            )
        }
    }
}