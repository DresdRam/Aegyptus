package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.model.Category
import sq.mayv.aegyptus.model.Place

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaceDataView(
    place: Place?,
    pagerState: PagerState,
    navController: NavController
) {
    val images = place?.images?.split("|")
        ?: listOf("https://i.ibb.co/3NFhvrD/546943212-caitbay-5.jpg")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top, unbounded = true),
    ) {

        PlaceImageView(
            pagerState = pagerState,
            images = images
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 35.dp)
                .padding(horizontal = 15.dp),
            text = place?.name ?: "Failed to load the name",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

    Card(
        modifier = Modifier
            .padding(top = 280.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
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
                        .background(colorResource(id = R.color.primary))
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 15.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            PlaceCategoryView(category = place?.category ?: Category())

            PlaceInfoView(
                location = place?.address ?: "Failed to load",
                time = place?.time ?: "Failed to load"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = place?.about ?: "Failed to load",
                color = Color.Black
            )
        }
    }
}