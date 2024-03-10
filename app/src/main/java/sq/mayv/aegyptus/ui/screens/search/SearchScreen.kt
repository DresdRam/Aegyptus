package sq.mayv.aegyptus.ui.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.ui.screens.search.components.PlacesListView
import sq.mayv.aegyptus.ui.screens.search.components.SearchTopBar

@Composable
fun SearchScreen(
    navController: NavController,
    searchQuery: String,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                navController = navController,
                searchQuery = searchQuery
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color.White
        ) {

            PlacesListView(
                places = listOf(Place(), Place(), Place(), Place(), Place()),
                onItemClick = {},
                onSaveClick = { placeId, isFavorite ->

                }
            )
        }
    }
}