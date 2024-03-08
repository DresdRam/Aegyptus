package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.R

@Composable
fun PlaceCategoryView(categoryId: Int) {
    Card(
        modifier = Modifier.size(45.dp),
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.primary)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = getCategoryIcon(categoryId)),
                contentDescription = "",
                tint = colorResource(id = R.color.primary)
            )
        }

    }

    Text(
        text = getCategoryName(categoryId),
        color = colorResource(id = R.color.description)
    )
}

private fun getCategoryName(id: Int): String {
    return when (id) {
        1 -> "Beach"
        2 -> "Cultural"
        3 -> "Entertainment"
        4 -> "Historical"
        5 -> "Religious"
        else -> "Historical"
    }
}

private fun getCategoryIcon(id: Int): Int {
    return when (id) {
        1 -> R.drawable.ic_beach
        2 -> R.drawable.ic_cultural
        3 -> R.drawable.ic_entertainment
        4 -> R.drawable.ic_historical
        5 -> R.drawable.ic_religious_2
        else -> R.drawable.ic_historical
    }
}