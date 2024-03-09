package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import sq.mayv.aegyptus.components.SearchTextField
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.home.HomeViewModel
import sq.mayv.aegyptus.util.CategoryItem

@Composable
fun HomeDataView(
    places: List<Place>?,
    rootNavController: NavController,
    viewModel: HomeViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        var searchQuery by remember { mutableStateOf("") }
        var trailingIconVisibility by remember { mutableStateOf(false) }

        SearchTextField(
            modifier = Modifier
                .padding(top = 25.dp)
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
            places = places ?: listOf(),
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
    }
}