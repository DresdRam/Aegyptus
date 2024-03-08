package sq.mayv.aegyptus.ui.screens.favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.model.Place

@Composable
fun FavoritesListView(
    favorites: List<Place>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(favorites, key = { it.id }) { favorite ->
            FavoritesListItem(
                favorite = favorite,
                onItemClick = { onItemClick(it) }
            )
        }
    }
}