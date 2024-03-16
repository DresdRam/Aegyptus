package sq.mayv.aegyptus.ui.screens.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sq.mayv.aegyptus.components.MessageView
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.search.components.PlacesListShimmer
import sq.mayv.aegyptus.ui.screens.search.components.PlacesListView
import sq.mayv.aegyptus.ui.screens.search.components.SearchErrorView
import sq.mayv.aegyptus.ui.screens.search.components.SearchTopBar

@Composable
fun SearchScreen(
    navController: NavController,
    searchQuery: String,
    viewModel: SearchViewModel = hiltViewModel()
) {

    var query by remember { mutableStateOf(searchQuery) }

    LaunchedEffect(key1 = query) {
        viewModel.getAllCategories()
        viewModel.search(query)
    }

    val categories by viewModel.categoriesData.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                navController = navController,
                searchQuery = searchQuery,
                categories = categories.data ?: listOf(),
                onSearchClick = {
                    if (it.isNotEmpty()) {
                        query = it
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color.White
        ) {

            val places by viewModel.placesData.collectAsState()

            AnimatedContent(
                targetState = viewModel.isLoading,
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
            ) { isLoading ->
                if (isLoading) {
                    PlacesListShimmer()
                } else {
                    AnimatedContent(
                        targetState = viewModel.isSuccessful,
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
                    ) { isSuccessful ->
                        if (isSuccessful) {
                            val size = places.data?.size ?: 0
                            AnimatedContent(
                                targetState = size > 0,
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
                            ) { isEmpty ->
                                if (isEmpty) {
                                    PlacesListView(
                                        places = places.data ?: listOf(),
                                        onItemClick = { placeId ->
                                            navController.navigate(
                                                AppScreens.PlaceScreen.name.plus(
                                                    placeId
                                                )
                                            )
                                        },
                                        onSaveClick = { placeId, isFavorite ->

                                        }
                                    )
                                } else {
                                    MessageView(message = "There is no result for this search query.")
                                }
                            }
                        } else {
                            SearchErrorView()
                        }
                    }
                }
            }

        }
    }
}