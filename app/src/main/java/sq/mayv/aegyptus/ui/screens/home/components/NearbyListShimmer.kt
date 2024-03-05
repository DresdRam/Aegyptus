package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.util.extension.shimmer

@Composable
fun NearbyListShimmer() {
    LazyRow(
        userScrollEnabled = false
    ) {
        items(4) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(230.dp)
                    .height(190.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )
        }
    }
}