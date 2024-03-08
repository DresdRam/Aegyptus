package sq.mayv.aegyptus.ui.screens.place

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.ui.screens.place.components.PlaceDataShimmer
import sq.mayv.aegyptus.ui.screens.place.components.PlaceDataView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaceScreen(
    navController: NavController,
    placeId: Int,
    viewModel: PlaceViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = placeId) {
        viewModel.getPlaceInfo(placeId)
    }

    val place = viewModel.placeData.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            AnimatedContent(
                targetState = !viewModel.isPlaceLoading && viewModel.isSuccessful,
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

                Box(modifier = Modifier.fillMaxWidth()) {

                    IconButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.TopStart)
                            .padding(start = 15.dp),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "Back Arrow Icon",
                            tint = Color.White
                        )
                    }

                    if (condition) {
                        var isFavorite by remember { mutableStateOf(place.value.data?.isFavorite ?: false) }

                        IconButton(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.TopEnd)
                                .padding(end = 15.dp),
                            onClick = {
                                if (!viewModel.isAddingFavorite && !viewModel.isRemovingFavorite) {
                                    if (!isFavorite) {
                                        viewModel.addToFavorites(placeId)
                                    } else {
                                        viewModel.removeFromFavorites(placeId)
                                    }
                                    isFavorite = !isFavorite
                                }
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = if (isFavorite) painterResource(id = R.drawable.ic_saved) else painterResource(
                                    id = R.drawable.ic_save
                                ),
                                contentDescription = "Save Place Icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White
        ) {

            val images = place.value.data?.images?.split("|") ?: listOf("")
            val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

            AnimatedContent(
                targetState = !viewModel.isPlaceLoading && viewModel.isSuccessful,
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
                if (condition) {
                    PlaceDataView(
                        place = place.value.data,
                        pagerState = pagerState,
                        navController = navController
                    )
                } else {
                    PlaceDataShimmer()
                }
            }
        }
    }
}