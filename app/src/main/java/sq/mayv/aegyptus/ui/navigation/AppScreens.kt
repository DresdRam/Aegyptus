package sq.mayv.aegyptus.ui.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    Auth,
    Home,
    WelcomeScreen,
    HomeScreen,
    LoginScreen;

    companion object {
        fun fromRoute(route: String): AppScreens
        = when(route.substringBefore('/')){
            LoginScreen.name -> LoginScreen
            HomeScreen.name -> HomeScreen
            WelcomeScreen.name -> WelcomeScreen
            else -> throw IllegalArgumentException("Route $route is not Recognised.")
        }
    }
}