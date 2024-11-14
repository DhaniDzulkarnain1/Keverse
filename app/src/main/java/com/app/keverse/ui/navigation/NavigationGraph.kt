package com.app.keverse.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.keverse.ui.screen.HomeScreen
import com.app.keverse.ui.screen.group.GroupScreen
import com.app.keverse.ui.screen.profil.ProfileScreen
import com.app.keverse.ui.screen.detail.AlbumDetailScreen
import com.app.keverse.ui.screen.group.GroupDetailScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        // Main routes
        composable(NavigationItem.Home.route) { HomeScreen(navController) }
        composable(NavigationItem.Group.route) { GroupScreen(navController) }
        composable(NavigationItem.Profile.route) { ProfileScreen(navController) }

        // Album detail
        composable(
            route = "album_detail/{albumId}/{source}",
            arguments = listOf(
                navArgument("albumId") { type = NavType.IntType },
                navArgument("source") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt("albumId") ?: 0
            val source = backStackEntry.arguments?.getString("source") ?: "recent"

            AlbumDetailScreen(
                albumId = albumId,
                source = source,
                navController = navController
            )
        }

        // Group detail
        composable(
            route = "group_detail/{groupId}",
            arguments = listOf(
                navArgument("groupId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getInt("groupId") ?: 0

            GroupDetailScreen(
                groupId = groupId,
                navController = navController
            )
        }
    }
}
