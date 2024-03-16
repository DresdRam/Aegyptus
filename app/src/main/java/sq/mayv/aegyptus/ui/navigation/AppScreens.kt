package sq.mayv.aegyptus.ui.navigation

enum class AppScreens {
    Auth,
    Main,
    WelcomeScreen,
    PlaceScreen,
    SearchScreen,
    HomeScreen,
    MainScreen,
    SignInScreen,
    SignUpScreen,
    RecoverPasswordScreen;

    companion object {
        fun fromRoute(route: String): AppScreens = when (route.substringBefore('/')) {
            SignInScreen.name -> SignInScreen
            SignUpScreen.name -> SignUpScreen
            HomeScreen.name -> HomeScreen
            WelcomeScreen.name -> WelcomeScreen
            else -> throw IllegalArgumentException("Route $route is not Recognised.")
        }
    }
}