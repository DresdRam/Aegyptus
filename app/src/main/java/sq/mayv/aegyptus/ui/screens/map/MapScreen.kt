package sq.mayv.aegyptus.ui.screens.map

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.components.LottieAnimationView
import sq.mayv.aegyptus.ui.screens.map.components.Map
import sq.mayv.aegyptus.ui.screens.map.components.MapErrorView
import sq.mayv.aegyptus.ui.screens.map.viewstate.MapViewState

@Composable
fun MapScreen(
    rootNavController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getAllPlaces()
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = viewState,
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
    ) {
        when (it) {

            MapViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimationView(
                        modifier = Modifier.size(120.dp),
                        lottie = R.raw.ankh_loading_black
                    )
                }
            }

            MapViewState.Failure -> {
                MapErrorView()
            }

            is MapViewState.Success -> {
                Map(
                    rootNavController = rootNavController,
                    viewModel = viewModel,
                    places = it.places,
                    mapType = it.mapType,
                    trafficMode = it.trafficMode
                )
            }
        }
    }

}