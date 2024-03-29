package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.extension.shimmer

@Composable
fun PlaceInfoShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {

            Box(
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {

            Box(
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )
        }
    }
}