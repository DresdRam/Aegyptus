package sq.mayv.aegyptus.components

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationView(
    modifier: Modifier = Modifier,
    @RawRes lottie: Int,
    iterateForEver: Boolean = true
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottie))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = if (iterateForEver) LottieConstants.IterateForever else 1,
    )

}