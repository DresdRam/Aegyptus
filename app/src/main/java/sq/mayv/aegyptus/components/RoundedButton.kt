package sq.mayv.aegyptus.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 22,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ),
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(15.dp),
        onClick = { onClicked() }
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}