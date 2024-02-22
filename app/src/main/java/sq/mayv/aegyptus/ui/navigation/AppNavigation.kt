package sq.mayv.aegyptus.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import sq.mayv.aegyptus.ui.screens.login.LoginScreen
import sq.mayv.aegyptus.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation(startDestination: String = AppScreens.WelcomeScreen.name) {
    val navigationController = rememberNavController()
    val transitionSpeed = 300

    NavHost(navController = navigationController, startDestination = startDestination) {

        navigation(
            startDestination = AppScreens.LoginScreen.name,
            route = "Auth"
        ) {
            composable(
                AppScreens.LoginScreen.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(transitionSpeed)
                    )
                }, exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(transitionSpeed)
                    )
                }, popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(transitionSpeed)
                    )
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(transitionSpeed)
                    )
                }) {
                LoginScreen(navController = navigationController)
            }
        }

        composable(
            AppScreens.WelcomeScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            WelcomeScreen(navController = navigationController)
        }

        navigation(
            startDestination = AppScreens.LoginScreen.name,
            route = "Home"
        ) {
            composable(
                AppScreens.LoginScreen.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(transitionSpeed)
                    )
                }, exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(transitionSpeed)
                    )
                }, popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(transitionSpeed)
                    )
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(transitionSpeed)
                    )
                }) {
                LoginScreen(navController = navigationController)
            }
        }
    }
}