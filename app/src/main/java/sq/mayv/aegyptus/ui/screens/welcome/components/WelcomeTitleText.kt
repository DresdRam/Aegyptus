package sq.mayv.aegyptus.ui.screens.welcome.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun WelcomeTitleText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: Int = 18,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
    fontFamily: FontFamily = FontFamily(Font(R.font.egyptian_nights, FontWeight.Bold))
) {

    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}