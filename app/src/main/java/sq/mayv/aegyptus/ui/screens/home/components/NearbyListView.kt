package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import sq.mayv.aegyptus.model.Place

@Composable
fun NearbyListView(places: List<Place>, onSaveClick: (Int, Boolean) -> Unit) {
    LazyRow() {
        items(items = places, key = { it.id }) { place ->
            NearbyListItem(
                place = place,
                onSaveClick = { id, isFavorite ->
                    onSaveClick(id, isFavorite)
                })
        }
    }
}