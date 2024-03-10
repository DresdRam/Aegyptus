package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.util.extension.shimmer

@Composable
fun HomeDataShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .padding(horizontal = 25.dp)
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(15.dp))
                .shimmer(),
        )

        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 15.dp)
                .height(25.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmer(),
        )

        HomePlacesListShimmer()

        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 15.dp)
                .height(25.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmer(),
        )

        HomePlacesListShimmer()

        Box(
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 15.dp)
                .height(25.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmer(),
        )

        CategoriesListShimmer()

    }
}