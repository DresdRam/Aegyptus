package sq.mayv.aegyptus.util

import androidx.annotation.RawRes
import sq.mayv.aegyptus.R

sealed class OnboardingPage (
    @RawRes
    val lottie: Int,
    val header: String,
    val description: String
) {

    data object FirstPage: OnboardingPage(
        lottie = R.raw.desert,
        header = "Nature",
        description = "Discover all of Egypt's natures and wonders"
    )

    data object ThirdPage: OnboardingPage(
        lottie = R.raw.travelling,
        header = "Location",
        description = "You can get the precise location and also use maps, just trust Aegyptus"
    )

    data object SecondPage: OnboardingPage(
        lottie = R.raw.app_guide,
        header = "Guide",
        description = "You don't need any tour guide, Aegyptus is here to help and guide you through all of your tours"
    )

}
