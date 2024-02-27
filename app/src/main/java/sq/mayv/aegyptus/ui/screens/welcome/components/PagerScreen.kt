package sq.mayv.aegyptus.ui.screens.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.components.LottieAnimationView
import sq.mayv.aegyptus.util.OnboardingPage

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        LottieAnimationView(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(0.75f),
            lottie = onboardingPage.lottie
        )

        WelcomeTitleText(
            modifier = Modifier
                .fillMaxWidth(),
            text = onboardingPage.header,
            color = Color.Black,
            fontSize = 30,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onboardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }
}