package sq.mayv.aegyptus.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.components.SearchTextField
import sq.mayv.aegyptus.ui.screens.home.components.CategoriesListShimmer
import sq.mayv.aegyptus.ui.screens.home.components.CategoriesListView
import sq.mayv.aegyptus.ui.screens.home.components.NearbyListShimmer
import sq.mayv.aegyptus.ui.screens.home.components.NearbyListView
import sq.mayv.aegyptus.util.CategoryItem
import sq.mayv.aegyptus.util.extension.shimmer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getNearbyPlaces()
        }
    }

    val places by viewModel.placesData.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        AnimatedContent(
            targetState = !viewModel.isPlacesLoading && viewModel.isSuccessful,
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
        ) { condition ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (condition) {
                    var searchQuery by remember { mutableStateOf("") }

                    SearchTextField(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .padding(horizontal = 25.dp)
                            .fillMaxWidth(),
                        search = searchQuery,
                        onValueChange = { value ->
                            searchQuery = value
                        },
                        onSearchClick = {  }
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 15.dp),
                        text = "Nearby",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )


                    NearbyListView(
                        places = places.data ?: listOf(),
                        onSaveClick = { id, isFavorite ->
                            coroutineScope.launch(Dispatchers.IO) {
                                if (!viewModel.isAddingFavorite && !viewModel.isRemovingFavorite) {
                                    if (!isFavorite) {
                                        viewModel.addToFavorites(id)
                                    } else {
                                        viewModel.removeFromFavorites(id)
                                    }
                                }
                            }
                        }
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .padding(horizontal = 15.dp),
                        text = "Categories",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    CategoriesListView(
                        categories = listOf(
                            CategoryItem.Beach,
                            CategoryItem.Cultural,
                            CategoryItem.Entertainment,
                            CategoryItem.Historical,
                            CategoryItem.Religious
                        )
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .padding(horizontal = 25.dp)
                            .fillMaxWidth()
                            .height(65.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .shimmer(),
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 15.dp)
                            .height(25.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .shimmer(),
                    )

                    NearbyListShimmer()

                    Box(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .padding(horizontal = 15.dp)
                            .height(25.dp)
                            .width(120.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .shimmer(),
                    )

                    CategoriesListShimmer()
                }
            }
        }
    }
}