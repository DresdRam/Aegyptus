package sq.mayv.aegyptus.util

import androidx.annotation.DrawableRes
import sq.mayv.aegyptus.R

sealed class BottomNavItem(val route: String, @DrawableRes val drawable: Int) {
    data object Home : BottomNavItem("home", R.drawable.ic_home)
    data object Map : BottomNavItem("map", R.drawable.ic_map)
    data object Favorite : BottomNavItem("saved", R.drawable.ic_saved)
    data object Profile : BottomNavItem("profile", R.drawable.ic_profile)
}
