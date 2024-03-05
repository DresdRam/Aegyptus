package sq.mayv.aegyptus.ui.screens.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
fun OutlinedProfileButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 20,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.White,
        contentColor = colorResource(id = R.color.primary)
    ),
    borderColor: Color = colorResource(id = R.color.primary),
    @DrawableRes trailingIcon: Int = R.drawable.ic_keyboard_arrow_right,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier.height(60.dp),
        colors = colors,
        shape = RoundedCornerShape(10.dp),
        onClick = { onClicked() },
        border = BorderStroke(2.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(9f),
                text = text,
                fontSize = fontSize.sp,
                textAlign = TextAlign.Start
            )

            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = trailingIcon),
                contentDescription = ""
            )
        }
    }
}