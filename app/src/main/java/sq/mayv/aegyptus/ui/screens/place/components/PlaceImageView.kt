package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun PlaceImageView(
    pagerState: PagerState,
    images: List<String>
) {

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        state = pagerState,
        outOfBoundsPageCount = 1
    ) { position ->
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            model = images[position],
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}