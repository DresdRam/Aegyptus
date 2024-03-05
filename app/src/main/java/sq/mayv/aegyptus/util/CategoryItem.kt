package sq.mayv.aegyptus.util

import androidx.annotation.DrawableRes
import sq.mayv.aegyptus.R

sealed class CategoryItem(val title: String, @DrawableRes val icon: Int) {
    data object Beach : CategoryItem("Beach", R.drawable.ic_beach)
    data object Cultural : CategoryItem("Cultural", R.drawable.ic_cultural)
    data object Entertainment : CategoryItem("Entertainment", R.drawable.ic_entertainment)
    data object Historical : CategoryItem("Historical", R.drawable.ic_historical)
    data object Religious : CategoryItem("Religious", R.drawable.ic_religious_2)
}
