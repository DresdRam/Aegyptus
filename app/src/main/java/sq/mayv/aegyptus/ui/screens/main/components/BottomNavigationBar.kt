package sq.mayv.aegyptus.ui.screens.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.util.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavController, bottomNavItems: List<BottomNavItem>) {

    Card(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(bottom = 30.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 20.dp
    ) {

        BottomNavigation(
            modifier = Modifier,
            backgroundColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.drawable),
                                contentDescription = null,
                                tint = if (currentRoute == item.route) colorResource(id = R.color.primary) else Color.Gray
                            )
                            AnimatedVisibility(visible = currentRoute == item.route) {

                                Card(
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .size(8.dp),
                                    backgroundColor = colorResource(id = R.color.primary),
                                    contentColor = colorResource(id = R.color.primary),
                                    shape = CircleShape,
                                    content = {}
                                )
                            }
                        }
                    },
                )
            }
        }
    }
}