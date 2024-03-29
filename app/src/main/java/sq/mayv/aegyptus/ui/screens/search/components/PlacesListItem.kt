package sq.mayv.aegyptus.ui.screens.search.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.model.Place

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlacesListItem(
    place: Place,
    onItemClick: (Int) -> Unit,
    onSaveClick: (Int, Boolean) -> Unit
) {

    val image = place.images.split('|')[0]

    var isFavorite by remember { mutableStateOf(place.isFavorite) }

    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                onItemClick(place.id)
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Column {
            Box {
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height(190.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    GlideImage(
                        model = image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = {
                        onSaveClick(place.id, isFavorite)
                        isFavorite = !isFavorite
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .size(26.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                    ) {
                        AnimatedContent(
                            targetState = isFavorite,
                            label = "",
                            transitionSpec = {
                                fadeIn(
                                    animationSpec = tween(250, easing = EaseIn)
                                ).togetherWith(
                                    fadeOut(
                                        animationSpec = tween(250, easing = EaseOut)
                                    )
                                )
                            }
                        ) { condition ->
                            Box(modifier = Modifier.fillMaxSize()) {
                                Icon(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = if (condition) painterResource(id = R.drawable.ic_heart_filled) else painterResource(
                                        id = R.drawable.ic_heart
                                    ),
                                    contentDescription = "",
                                    tint = if (condition) Color.Red else colorResource(id = R.color.primary)
                                )
                            }
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                text = place.name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}