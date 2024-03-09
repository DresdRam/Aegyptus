package sq.mayv.aegyptus.ui.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import sq.mayv.aegyptus.MainActivity
import sq.mayv.aegyptus.ui.screens.main.components.BottomNavigationBar
import sq.mayv.aegyptus.ui.screens.main.components.MainNavigationHost
import sq.mayv.aegyptus.util.BottomNavItem
import sq.mayv.aegyptus.util.LocationPermissionTextProvider
import sq.mayv.aegyptus.util.PermissionDialog
import sq.mayv.aegyptus.util.extension.openAppSettings

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(rootNavController: NavController) {

    val activity = LocalContext.current as MainActivity

    val navController = rememberNavController()

    val viewModel = viewModel<MainViewModel>()
    val dialogQueue = viewModel.visiblePermissionDialogQueue

    var isPermissionGranted by remember { mutableStateOf(false) }

    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->

            Log.i("Permission", "MainScreen: onPermissionResult: isGranted -> $isGranted")

            if (isGranted) {
                isPermissionGranted = true
            }

            viewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

    LaunchedEffect(key1 = true) {
        locationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    dialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        LocationPermissionTextProvider()
                    }

                    else -> return@forEach
                },
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                    activity,
                    permission
                ),
                onDismiss = viewModel::dismissDialog,
                onOkClick = {
                    viewModel.dismissDialog()
                    locationPermissionResultLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                },
                onGoToAppSettingsClick = activity::openAppSettings
            )
        }

    AnimatedContent(
        targetState = isPermissionGranted,
        label = "",
        transitionSpec = {
            fadeIn(
                animationSpec = tween(600, easing = EaseIn)
            ).togetherWith(
                fadeOut(
                    animationSpec = tween(600, easing = EaseOut)
                )
            )
        }
    ) { condition ->

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
            if (condition) {
                MainNavigationHost(
                    rootNavController = rootNavController,
                    navController = navController
                )
            }
        }
    }

}