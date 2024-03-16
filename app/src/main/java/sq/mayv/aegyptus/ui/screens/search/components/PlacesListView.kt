package sq.mayv.aegyptus.ui.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.model.Place

@Composable
fun PlacesListView(
    places: List<Place>,
    onItemClick: (Int) -> Unit,
    onSaveClick: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(top = 15.dp, bottom = 25.dp),
    ) {
        items(places) { favorite ->
            PlacesListItem(
                place = favorite,
                onItemClick = { onItemClick(it) },
                onSaveClick = { placeId, isFavorite ->
                    onSaveClick(placeId, isFavorite)
                }
            )
        }
    }
}