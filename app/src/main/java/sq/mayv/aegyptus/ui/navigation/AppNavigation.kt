package sq.mayv.aegyptus.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import sq.mayv.aegyptus.ui.screens.main.MainScreen
import sq.mayv.aegyptus.ui.screens.recover_password.RecoverPasswordScreen
import sq.mayv.aegyptus.ui.screens.signin.SignInScreen
import sq.mayv.aegyptus.ui.screens.signup.SignUpScreen
import sq.mayv.aegyptus.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation(startDestination: String = AppScreens.WelcomeScreen.name) {
    val navigationController = rememberNavController()
    val transitionSpeed = 300

    NavHost(navController = navigationController, startDestination = startDestination) {

        navigation(
            startDestination = AppScreens.SignInScreen.name,
            route = "Auth"
        ) {
            composable(
                AppScreens.SignInScreen.name,
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
                SignInScreen(navController = navigationController)
            }

            composable(
                AppScreens.RecoverPasswordScreen.name,
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
                RecoverPasswordScreen(navController = navigationController)
            }

            composable(
                AppScreens.SignUpScreen.name,
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
                SignUpScreen(navController = navigationController)
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
            startDestination = AppScreens.MainScreen.name,
            route = "Main"
        ) {
            composable(
                AppScreens.MainScreen.name,
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
                MainScreen(rootNavController = navigationController)
            }
        }
    }
}