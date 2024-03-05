package sq.mayv.aegyptus.ui.screens.favorites.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.model.Place

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun FavoritesListItem(favorite: Place) {

    val images = favorite.images.split('|')
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .height(150.dp),
    ) {

        Card(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 160.dp, bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = favorite.name,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = favorite.about,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.description),
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterEnd),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            Surface {

                HorizontalPager(
                    modifier = Modifier
                        .size(150.dp),
                    state = pagerState,
                    verticalAlignment = Alignment.Top
                ) { position ->
                    GlideImage(
                        modifier = Modifier.size(150.dp),
                        model = images[position],
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

                // Pager Indicator
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val width by animateDpAsState(
                            targetValue = if (pagerState.currentPage == iteration) 15.dp else 5.dp,
                            animationSpec = tween(
                                durationMillis = 100,
                            ),
                            label = "Indicator Width Animation"
                        )

                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .height(5.dp)
                                .width(width)
                                .clip(RoundedCornerShape(3.dp))
                                .background(colorResource(id = R.color.gold))
                        )
                    }
                }
            }
        }
    }
}