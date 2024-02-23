package com.atech.link_saver.ui.comman

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.atech.link_saver.R
import com.atech.link_saver.ui.activity.main.NavRoutes
import com.atech.link_saver.ui.theme.LinkSaverTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomAppbar: @Composable () -> Unit = {},
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
        },
        bottomBar = bottomAppbar
    ) {
        content(it, scrollState)
    }
}


@Composable
fun BottomAppbar(
    modifier: Modifier = Modifier,
    backStackEntry: State<NavBackStackEntry?> = remember { mutableStateOf(null) },
    onClick: (route: String) -> Unit = {},
) {
    val navigationItems = listOf(
        NavBarModel(
            title = R.string.Home,
            selectedIcon = Icons.Outlined.Dashboard,
            route = NavRoutes.HomeScreen.route
        ),
        NavBarModel(
            title = R.string.Archive,
            selectedIcon = Icons.Outlined.Archive,
            route = NavRoutes.ArchiveScreen.route
        ),
        NavBarModel(
            title = R.string.Bin,
            selectedIcon = Icons.Outlined.Delete,
            route = NavRoutes.BinScreen.route
        ),
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f)
                .then(modifier)
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    shape = RoundedCornerShape(percent = 100)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationItems.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavBarItem(
                    navItem = item,
                    isSelected = selected,
                    onClick = onClick
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "content description",
                )
            }

        }
    }
}

@Composable
fun RowScope.NavBarItem(
    navItem: NavBarModel,
    isSelected: Boolean,
    onClick: (route: String) -> Unit,
) {
    val mutableInteraction = remember { MutableInteractionSource() }
    val selectedColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent,
        label = "selectedColor"
    )
    val selectedIconColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "selectedIconColor"
    )
    Box(
        modifier = Modifier
            .height(64.dp)
            .weight(1f)
            // Dummy clickable to intercept clicks from passing under the container
            .clickable(
                indication = null,
                interactionSource = mutableInteraction,
                onClick = {}
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(64.dp)
                .background(
                    color = selectedColor,
                    shape = RoundedCornerShape(percent = 100)
                )
                .clip(RoundedCornerShape(100))
                .clickable { if (!isSelected) onClick(navItem.route) },
        )
        Icon(
            modifier = Modifier
                .size(22.dp),
            imageVector = navItem.selectedIcon,
            contentDescription = "navItem.title",
            tint = selectedIconColor
        )
    }
}

data class NavBarModel(
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector? = null,
    val route: String = "",
    val isVisible: Boolean = true
)


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MainContainerPreview() {
    LinkSaverTheme {
        BottomAppbar()
    }
}