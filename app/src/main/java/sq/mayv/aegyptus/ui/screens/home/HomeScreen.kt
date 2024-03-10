package sq.mayv.aegyptus.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sq.mayv.aegyptus.ui.screens.home.components.HomeDataShimmer
import sq.mayv.aegyptus.ui.screens.home.components.HomeDataView
import sq.mayv.aegyptus.ui.screens.home.components.HomeErrorView

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    rootNavController: NavController
) {

    LaunchedEffect(key1 = true) {
        viewModel.getNearbyPlaces()
        viewModel.getMostVisitedPlaces()
    }

    val nearby by viewModel.nearbyData.collectAsState()
    val mostVisited by viewModel.mostVisitedData.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        AnimatedContent(
            targetState = viewModel.isNearbyLoading || viewModel.isMostVisitedLoading,
            label = "Shimmer Transition",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(600, easing = EaseIn)
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(600, easing = EaseOut)
                    )
                )
            }
        ) { isLoading ->
            if (isLoading) {
                HomeDataShimmer()
            } else {
                AnimatedContent(
                    targetState = viewModel.isNearbySuccessful && viewModel.isMostVisitedSuccessful,
                    label = "Shimmer Transition",
                    transitionSpec = {
                        fadeIn(
                            animationSpec = tween(600, easing = EaseIn)
                        ).togetherWith(
                            fadeOut(
                                animationSpec = tween(600, easing = EaseOut)
                            )
                        )
                    }
                ) { isSuccessful ->
                    if (isSuccessful) {
                        HomeDataView(
                            nearby = nearby.data,
                            mostVisited =  mostVisited.data,
                            rootNavController = rootNavController,
                            viewModel = viewModel
                        )
                    } else {
                        HomeErrorView()
                    }
                }
            }
        }
    }
}
