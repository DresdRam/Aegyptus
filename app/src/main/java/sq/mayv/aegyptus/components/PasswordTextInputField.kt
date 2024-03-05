package sq.mayv.aegyptus.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aegyptus.R

@Composable
fun PasswordTextInputField(
    modifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    message: String = "",
    messageVisibility: Boolean = false
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val alpha by animateFloatAsState(
            targetValue = if (messageVisibility) 1f else 0f,
            label = "Error Visibility"
        )
        var passwordVisible by remember {
            mutableStateOf(false)
        }

        TextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.primary),
                cursorColor = colorResource(id = R.color.primary),
                focusedLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    AnimatedContent(
                        targetState = passwordVisible,
                        label = "",
                        contentAlignment = Alignment.Center
                        ) {

                        Icon(
                            painter = painterResource(
                                id = if (it) R.drawable.pharaoh_eye_opened else R.drawable.pharaoh_eye_closed
                            ),
                            tint = if (it) colorResource(id = R.color.gold) else LocalContentColor.current.copy(
                                alpha = LocalContentAlpha.current
                            ),
                            contentDescription = "Trailing Icon"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                autoCorrect = false
            ),
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = if (!passwordVisible) FontFamily(Font(R.font.yiroglyphics)) else FontFamily.Default,
                fontSize = if(!passwordVisible) 20.sp else TextUnit.Unspecified
            )
        )

        AnimatedVisibility(visible = messageVisibility) {
            Text(
                text = message,
                modifier = errorModifier
                    .padding(top = 10.dp)
                    .alpha(alpha),
                color = colorResource(id = R.color.gold)
            )
        }
    }
}