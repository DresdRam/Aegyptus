package sq.mayv.aegyptus.ui.screens.main

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import sq.mayv.aegyptus.MainActivity
import sq.mayv.aegyptus.components.LottieAnimationView
import sq.mayv.aegyptus.components.MessageView
import sq.mayv.aegyptus.data.PermissionEvent
import sq.mayv.aegyptus.extension.hasLocationPermission
import sq.mayv.aegyptus.extension.openAppSettings
import sq.mayv.aegyptus.ui.screens.main.components.MainView
import sq.mayv.aegyptus.ui.screens.main.viewstate.MainViewState
import sq.mayv.aegyptus.util.LocationPermissionTextProvider
import sq.mayv.aegyptus.util.PermissionDialog

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    rootNavController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val context = LocalContext.current as MainActivity

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val settingsResults =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                if (permissionState.allPermissionsGranted) {
                    viewModel.handleViewState(PermissionEvent.Granted)
                }
            }
        )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(!context.hasLocationPermission()) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            LaunchedEffect(Unit) {
                viewModel.handleViewState(PermissionEvent.Granted)
            }
        }

        permissionState.shouldShowRationale -> {
            PermissionDialog(permissionTextProvider = LocationPermissionTextProvider(),
                onDismiss = {},
                onOkClick = {
                    permissionState.launchMultiplePermissionRequest()
                }
            )
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            LaunchedEffect(Unit) {
                viewModel.handleViewState(PermissionEvent.Revoked)
            }
        }
    }

    AnimatedContent(
        targetState = viewState,
        label = "ViewState Animation",
        transitionSpec = {
            fadeIn(
                animationSpec = tween(600, easing = EaseIn)
            ).togetherWith(
                fadeOut(
                    animationSpec = tween(600, easing = EaseOut)
                )
            )
        }) {
        when (it) {
            MainViewState.CheckingPermissions -> {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        LottieAnimationView(
                            modifier = Modifier.size(100.dp),
                            lottie = sq.mayv.aegyptus.R.raw.ankh_loading_black
                        )
                    }
                }
            }

            MainViewState.RevokedPermissions -> {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MessageView(message = "Aegyptus needs the location permissions.",
                        buttonEnabled = true,
                        buttonMessage = "Allow Permissions",
                        onButtonClick = { context.openAppSettings(settingsResults) }
                    )
                }
            }

            MainViewState.GrantedPermissions -> {
                MainView(rootNavController = rootNavController)
            }
        }
    }
}