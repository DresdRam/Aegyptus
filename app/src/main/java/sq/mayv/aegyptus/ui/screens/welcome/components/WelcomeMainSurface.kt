package sq.mayv.aegyptus.ui.screens.welcome.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.util.OnboardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeMainSurface(navController: NavController) {
    val pages = listOf(
        OnboardingPage.FirstPage,
        OnboardingPage.ThirdPage,
        OnboardingPage.SecondPage
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PagerFinishButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Let's start exploring",
                    fontSize = 22,
                    pagesNum = pages.size,
                    pagerState = pagerState,
                    onClicked = {
                        navController.popBackStack()
                        navController.navigate(AppScreens.Auth.name)
                    }
                )
            }
        },
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .weight(10f),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onboardingPage = pages[position])
            }

            // Pager Indicator
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val width by animateDpAsState(
                        targetValue = if (pagerState.currentPage == iteration) 18.dp else 8.dp,
                        animationSpec = tween(
                            durationMillis = 100,
                        ),
                        label = "Indicator Width Animation"
                    )

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .height(8.dp)
                            .width(width)
                            .clip(RoundedCornerShape(3.dp))
                            .background(colorResource(id = R.color.primary))
                    )
                }
            }
        }
    }
}