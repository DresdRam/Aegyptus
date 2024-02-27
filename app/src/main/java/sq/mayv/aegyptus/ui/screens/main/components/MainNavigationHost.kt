package sq.mayv.aegyptus.ui.screens.main.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sq.mayv.aegyptus.ui.screens.home.HomeScreen
import sq.mayv.aegyptus.util.BottomNavItem

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen(navController = navController) }
        composable(BottomNavItem.Map.route) { }
        composable(BottomNavItem.Favorite.route) { }
        composable(BottomNavItem.Profile.route) { }
    }
}