package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import sq.mayv.aegyptus.util.CategoryItem

@Composable
fun CategoriesListView(categories: List<CategoryItem>) {
    LazyRow {
        items(items = categories) { category ->
            CategoriesListItem(category.icon, category.title)
        }
    }
}