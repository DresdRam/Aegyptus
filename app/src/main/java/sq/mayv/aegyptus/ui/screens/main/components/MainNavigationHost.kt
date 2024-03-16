package sq.mayv.aegyptus.ui.screens.main.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sq.mayv.aegyptus.ui.screens.favorites.FavoritesScreen
import sq.mayv.aegyptus.ui.screens.home.HomeScreen
import sq.mayv.aegyptus.ui.screens.map.MapScreen
import sq.mayv.aegyptus.ui.screens.profile.ProfileScreen
import sq.mayv.aegyptus.util.BottomNavItem

@Composable
fun MainNavigationHost(rootNavController: NavController, navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(
                rootNavController = rootNavController,
                navController = navController
            )
        }
        composable(BottomNavItem.Map.route) {
            MapScreen(rootNavController = rootNavController)
        }
        composable(BottomNavItem.Favorite.route) {
            FavoritesScreen(rootNavController = rootNavController, navController = navController)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(rootNavController = rootNavController, navController = navController)
        }
    }
}