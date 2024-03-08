package sq.mayv.aegyptus

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.provider.Settings
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import sq.mayv.aegyptus.ui.navigation.AppNavigation
import sq.mayv.aegyptus.ui.screens.splash.SplashViewModel
import sq.mayv.aegyptus.ui.theme.AegyptusTheme
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import sq.mayv.aegyptus.util.LocationPermissionTextProvider
import sq.mayv.aegyptus.util.PermissionDialog

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var pressedTime: Long = 0
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
    )

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {

//            val viewModel = viewModel<MainViewModel>()
//            val dialogQueue = viewModel.visiblePermissionDialogQueue
//
//            val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestMultiplePermissions(),
//                onResult = { permissions ->
//                    permissionsToRequest.forEach { permission ->
//                        viewModel.onPermissionResult(
//                            permission = permission,
//                            isGranted = permissions[permission] == true
//                        )
//                    }
//                }
//            )
//
//            dialogQueue
//                .reversed()
//                .forEach { permission ->
//                    PermissionDialog(
//                        permissionTextProvider = when (permission) {
//                            Manifest.permission.ACCESS_FINE_LOCATION -> {
//                                LocationPermissionTextProvider()
//                            }
//                            Manifest.permission.ACCESS_COARSE_LOCATION -> {
//                                LocationPermissionTextProvider()
//                            }
//                            Manifest.permission.ACCESS_BACKGROUND_LOCATION -> {
//                                LocationPermissionTextProvider()
//                            }
//                            else -> return@forEach
//                        },
//                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
//                            permission
//                        ),
//                        onDismiss = viewModel::dismissDialog,
//                        onOkClick = {
//                            viewModel.dismissDialog()
//                            multiplePermissionResultLauncher.launch(
//                                arrayOf(permission)
//                            )
//                        },
//                        onGoToAppSettingsClick = ::openAppSettings
//                    )
//                }

            AegyptusTheme {
                AppMain(splashViewModel.startDestination.value)
            }
        }

        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    finish()
                } else {
                    Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                }

                pressedTime = System.currentTimeMillis()
            }
        } else {
            onBackPressedDispatcher.addCallback(this) {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    finish()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Press back again to exit",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                pressedTime = System.currentTimeMillis()
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

//fun Activity.openAppSettings() {
//    Intent(
//        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//        Uri.fromParts("package", packageName, null)
//    ).also(::startActivity)
//}

