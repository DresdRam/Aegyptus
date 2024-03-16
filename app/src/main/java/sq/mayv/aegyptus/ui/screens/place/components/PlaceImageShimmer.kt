package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.extension.shimmer

@Composable
fun PlaceImageShimmer() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shimmer()
    )
}