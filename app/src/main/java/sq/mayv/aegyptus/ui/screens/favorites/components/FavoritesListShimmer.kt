package sq.mayv.aegyptus.ui.screens.favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.util.extension.shimmer

@Composable
fun FavoritesListShimmer() {
    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        repeat(4) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .height(150.dp),
            ) {

                Box(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                        .fillMaxSize()
                        .shimmer(),
                )

                Card(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    elevation = CardDefaults.cardElevation(15.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .shimmer(),
                    )
                }
            }
        }
    }
}