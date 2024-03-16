package sq.mayv.aegyptus.util

import androidx.annotation.DrawableRes
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.model.Category
import java.util.Locale

sealed class CategoryItem(val title: String, @DrawableRes val icon: Int) {
    data object Beach : CategoryItem("Beach", R.drawable.ic_beach)
    data object Cultural : CategoryItem("Cultural", R.drawable.ic_cultural)
    data object Entertainment : CategoryItem("Entertainment", R.drawable.ic_entertainment)
    data object Historical : CategoryItem("Historical", R.drawable.ic_historical)
    data object Religious : CategoryItem("Religious", R.drawable.ic_religious)

    companion object {
        fun getCategoryItems(categories: List<Category>): List<CategoryItem> {
            val list = listOf<CategoryItem>().toMutableList()

            for (category in categories) {
                list.add(
                    getCategoryItem(category)
                )
            }

            return list
        }

        fun getCategoryItem(category: Category): CategoryItem {
            return when (category.category.lowercase(locale = Locale.ENGLISH)) {
                "beach" -> Beach
                "cultural" -> Cultural
                "entertainment" -> Entertainment
                "historical" -> Historical
                "religious" -> Religious
                else -> Historical
            }
        }
    }
}
