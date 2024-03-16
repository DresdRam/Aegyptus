package sq.mayv.aegyptus.ui.screens.favorites

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.favorites.components.FavoritesErrorView
import sq.mayv.aegyptus.ui.screens.favorites.components.FavoritesListShimmer
import sq.mayv.aegyptus.ui.screens.favorites.components.FavoritesListView
import sq.mayv.aegyptus.ui.screens.favorites.viewstate.FavoritesViewState

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
    rootNavController: NavController
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllFavorites()
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (it) {
                    FavoritesViewState.Loading -> {
                        FavoritesListShimmer()
                    }

                    FavoritesViewState.Failure -> {
                        FavoritesErrorView()
                    }

                    is FavoritesViewState.Success -> {
                        FavoritesListView(
                            favorites = it.places,
                            onItemClick = {
                                rootNavController.navigate(
                                    AppScreens.PlaceScreen.name.plus(
                                        it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}