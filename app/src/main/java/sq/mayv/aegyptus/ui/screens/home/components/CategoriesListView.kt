package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.util.CategoryItem

@Composable
fun CategoriesListView(categories: List<CategoryItem>) {
    LazyRow(
        modifier = Modifier
        .padding(bottom = 120.dp)
    ) {
        items(items = categories) { category ->
            CategoriesListItem(category.icon, category.title)
        }
    }
}