package sq.mayv.aegyptus.ui.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.extension.shimmer

@Composable
fun PlacesListShimmer() {
    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        repeat(4) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(250.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = CardDefaults.cardElevation(15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmer()
                )
            }
        }
    }
}