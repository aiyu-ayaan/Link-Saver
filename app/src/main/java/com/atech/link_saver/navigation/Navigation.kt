package com.atech.link_saver.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.atech.link_saver.ui.screens.home.compose.HomeScreen
import com.atech.link_saver.utils.animatedComposable

sealed class NavRoutes(val route: String) {
    data object HomeScreen : NavRoutes("home_screen")
    data object AddLinkScreen : NavRoutes("add_link_screen")

    data object LinkDetailScreen : NavRoutes("link_detail_screen")

    data object ArchiveScreen : NavRoutes("archive_screen")
}


@Composable
fun MainNavigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.HomeScreen.route
    ) {
        animatedComposable(
            route = NavRoutes.HomeScreen.route,
        ) {
            HomeScreen()
        }
    }
}