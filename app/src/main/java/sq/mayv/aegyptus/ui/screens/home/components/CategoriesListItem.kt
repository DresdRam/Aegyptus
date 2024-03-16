package sq.mayv.aegyptus.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun CategoriesListItem(@DrawableRes icon: Int, title: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(38.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}