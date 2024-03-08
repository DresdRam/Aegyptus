package sq.mayv.aegyptus.ui.screens.place.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun PlaceInfoView(location: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary)
                )

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = location,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.description)
                )
            }
        }


        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary)
                )

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = time,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.description)
                )
            }
        }
    }
}