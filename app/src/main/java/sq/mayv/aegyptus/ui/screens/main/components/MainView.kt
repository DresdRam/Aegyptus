package sq.mayv.aegyptus.ui.screens.main.components

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import sq.mayv.aegyptus.util.BottomNavItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainView(
    rootNavController: NavController,
) {

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
        MainNavigationHost(
            rootNavController = rootNavController,
            navController = navController
        )
    }
}
