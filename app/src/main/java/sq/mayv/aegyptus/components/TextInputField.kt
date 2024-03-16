package sq.mayv.aegyptus.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.R

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    messageModifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    message: String = "",
    messageVisibility: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        autoCorrect = false
    )
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val alpha by animateFloatAsState(
            targetValue = if (messageVisibility) 1f else 0f,
            label = "Error Visibility"
        )

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
            singleLine = true,
            keyboardOptions = keyboardOptions
        )

        AnimatedVisibility(visible = messageVisibility) {

            Text(
                text = message,
                modifier = messageModifier
                    .padding(top = 10.dp)
                    .alpha(alpha),
                color = colorResource(id = R.color.gold)
            )
        }
    }
}