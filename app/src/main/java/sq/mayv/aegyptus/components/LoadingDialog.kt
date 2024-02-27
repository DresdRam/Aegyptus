package sq.mayv.aegyptus.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import sq.mayv.aegyptus.R

@Composable
fun LoadingDialog(onDismissRequest: () -> Unit) {

    val properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = properties
    ) {
        Card(
            modifier = Modifier
                .size(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize())
            {
                LottieAnimationView(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    lottie = R.raw.ankh_loading_black
                )
            }
        }
    }
}