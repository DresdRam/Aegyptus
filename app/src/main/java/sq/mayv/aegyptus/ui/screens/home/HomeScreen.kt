package sq.mayv.aegyptus.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import sq.mayv.aegyptus.components.OutlinedMessageView
import sq.mayv.aegyptus.components.SearchTextField
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.home.components.CategoriesListShimmer
import sq.mayv.aegyptus.ui.screens.home.components.CategoriesListView
import sq.mayv.aegyptus.ui.screens.home.components.HomePlacesListShimmer
import sq.mayv.aegyptus.ui.screens.home.components.HomePlacesListView
import sq.mayv.aegyptus.ui.screens.home.viewstate.CategoriesViewState
import sq.mayv.aegyptus.ui.screens.home.viewstate.PlacesViewState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    rootNavController: NavController
) {

    LaunchedEffect(key1 = true) {
        viewModel.getNearbyPlaces()
        viewModel.getMostVisitedPlaces()
        viewModel.getCategories()
    }

    val nearbyViewState by viewModel.nearbyViewState.collectAsStateWithLifecycle()
    val mostVisitedViewState by viewModel.mostVisitedViewState.collectAsStateWithLifecycle()
    val categoriesViewState by viewModel.categoriesViewState.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }
    var trailingIconVisibility by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            SearchTextField(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                search = searchQuery,
                trailingIconVisibility = trailingIconVisibility,
                onValueChange = { value ->
                    searchQuery = value
                    if (searchQuery.isNotEmpty()) {
                        if (!trailingIconVisibility) {
                            trailingIconVisibility = true
                        }
                    } else {
                        trailingIconVisibility = false
                    }
                },
                onSearchClick = {
                    rootNavController.navigate(AppScreens.SearchScreen.name.plus(searchQuery))
                },
                onTrailingIconClick = {
                    searchQuery = ""
                    trailingIconVisibility = false
                }
            )

            AnimatedContent(
                targetState = nearbyViewState,
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
            ) { viewState ->
                when (viewState) {
                    PlacesViewState.Loading -> {
                        HomePlacesListShimmer()
                    }

                    PlacesViewState.Failure -> {
                        OutlinedMessageView(
                            message = "Failed to load nearby places!",
                            textColor = Color.Red,
                            outline = BorderStroke(1.dp, Color.Red)
                        )
                    }

                    is PlacesViewState.Success -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 15.dp),
                                text = "Nearby",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )

                            HomePlacesListView(
                                places = viewState.places,
                                emptyMessage = "There is no nearby places to your location, try increasing the nearby threshold from the settings.",
                                onItemClick = {
                                    rootNavController.navigate(AppScreens.PlaceScreen.name.plus(it))
                                },
                                onSaveClick = { id, isFavorite ->
                                    if (!viewModel.isAddingFavorite && !viewModel.isRemovingFavorite) {
                                        if (!isFavorite) {
                                            viewModel.addToFavorites(id)
                                        } else {
                                            viewModel.removeFromFavorites(id)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            AnimatedContent(
                targetState = mostVisitedViewState,
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
            ) { viewState ->
                when (viewState) {
                    PlacesViewState.Loading -> {
                        HomePlacesListShimmer()
                    }

                    PlacesViewState.Failure -> {
                        OutlinedMessageView(
                            message = "Failed to load most visited places!",
                            textColor = Color.Red,
                            outline = BorderStroke(1.dp, Color.Red)
                        )
                    }

                    is PlacesViewState.Success -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 15.dp),
                                text = "Most Visited",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )

                            HomePlacesListView(
                                places = viewState.places,
                                emptyMessage = "We haven't added most visited places to your governorate yet.",
                                onItemClick = {
                                    rootNavController.navigate(AppScreens.PlaceScreen.name.plus(it))
                                },
                                onSaveClick = { id, isFavorite ->
                                    if (!viewModel.isAddingFavorite && !viewModel.isRemovingFavorite) {
                                        if (!isFavorite) {
                                            viewModel.addToFavorites(id)
                                        } else {
                                            viewModel.removeFromFavorites(id)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            AnimatedContent(
                targetState = categoriesViewState,
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
            ) { viewState ->

                when (viewState) {
                    CategoriesViewState.Loading -> {
                        CategoriesListShimmer()
                    }

                    CategoriesViewState.Failure -> {
                        OutlinedMessageView(
                            message = "Failed to load categories!",
                            textColor = Color.Red,
                            outline = BorderStroke(1.dp, Color.Red)
                        )
                    }

                    is CategoriesViewState.Success -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 15.dp),
                                text = "Categories",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )

                            CategoriesListView(categories = viewState.categories)
                        }
                    }
                }
            }
        }
    }
}
