package sq.mayv.aegyptus.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun OutlinedRoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 22,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.White,
        contentColor = colorResource(id = R.color.gold)
    ),
    borderColor: Color = colorResource(id = R.color.gold),
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(10.dp),
        onClick = { onClicked() },
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center
        )
    }
}