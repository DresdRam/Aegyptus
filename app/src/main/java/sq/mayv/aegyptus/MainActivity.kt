package sq.mayv.aegyptus

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sq.mayv.aegyptus.ui.navigation.AppNavigation
import sq.mayv.aegyptus.ui.screens.splash.SplashViewModel
import sq.mayv.aegyptus.ui.theme.AegyptusTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        Log.i("Main Activity", "Start Destination -> ${splashViewModel.startDestination.value}")

        installSplashScreen().setKeepOnScreenCondition {
            Log.i("Main Activity", "ViewModel Is Loading -> ${splashViewModel.isLoading.value}")
            Log.i("Main Activity", "Start Destination -> ${splashViewModel.startDestination.value}")
            splashViewModel.isLoading.value
        }

        setContent {
            AegyptusTheme {
                AppMain(splashViewModel.startDestination.value)
            }
        }
    }
}

@Composable
private fun AppMain(startDestination: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppNavigation(startDestination)
        }
    }
}
