package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.util.extension.shimmer

@Composable
fun PlaceCategoryShimmer() {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .shimmer(),
    )

    Box(
        modifier = Modifier
            .width(120.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(10.dp))
            .shimmer()
    )
}