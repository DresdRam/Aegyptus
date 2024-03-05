package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.model.Place

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NearbyListItem(place: Place, onSaveClick: (Int, Boolean) -> Unit) {

    var isFavorite by remember { mutableStateOf(place.isFavorite) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(230.dp)
            .height(190.dp),
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
                        .fillMaxWidth()
                        .height(130.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    val split = place.images.split('|')

                    GlideImage(
                        model = split[0],
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
                            .size(24.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(3.dp),
                            painter = if (isFavorite) painterResource(id = R.drawable.ic_saved) else painterResource(
                                id = R.drawable.ic_save
                            ),
                            contentDescription = "",
                            tint = colorResource(id = R.color.primary)
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = place.name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${place.distanceInMeters} meters away",
                color = colorResource(id = R.color.orange),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}