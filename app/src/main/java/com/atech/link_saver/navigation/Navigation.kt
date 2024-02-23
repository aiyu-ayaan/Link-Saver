package com.atech.link_saver.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.atech.core.room.link.LinkModel
import com.atech.link_saver.ui.screens.home.HomeScreenState
import com.atech.link_saver.ui.screens.home.compose.HomeScreen
import com.atech.link_saver.utils.animatedComposable

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
fun MainNavigation(
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
            HomeScreen(
                state = HomeScreenState(),
                navHostController = navHostController
            )
        }
    }
}