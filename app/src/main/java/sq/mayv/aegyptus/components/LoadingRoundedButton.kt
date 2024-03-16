package sq.mayv.aegyptus.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun LoadingRoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    @RawRes lottie: Int = R.raw.ankh_loading_white,
    fontSize: Int = 22,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.primary),
        contentColor = Color.White
    ),
    isLoading: Boolean = false,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(10.dp),
        onClick = { onClicked() }
    ) {

        if (isLoading) {
            LottieAnimationView(
                modifier = Modifier.size(40.dp),
                lottie = lottie
            )
        } else {
            Text(
                text = text,
                fontSize = fontSize.sp
            )
        }
    }
}