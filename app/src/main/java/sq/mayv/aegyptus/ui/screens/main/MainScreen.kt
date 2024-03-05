package sq.mayv.aegyptus.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import sq.mayv.aegyptus.ui.screens.main.components.BottomNavigationBar
import sq.mayv.aegyptus.ui.screens.main.components.MainNavigationHost
import sq.mayv.aegyptus.util.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(rootNavController: NavController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                bottomNavItems = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Map,
                    BottomNavItem.Favorite,
                    BottomNavItem.Profile
                )
            )
        }
    ) {
        MainNavigationHost(rootNavController = rootNavController, navController = navController)
    }

}