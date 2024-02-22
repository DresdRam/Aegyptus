package sq.mayv.aegyptus.ui.screens.welcome.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.components.RoundedButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerFinishButton(
    modifier: Modifier,
    pagesNum: Int,
    pagerState: PagerState,
    text: String,
    fontSize: Int = 22,
    onClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 25.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == pagesNum - 1
        ) {
            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClicked = { onClicked() },
                text = text,
                fontSize = fontSize,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.primary),
                    contentColor = Color.White
                )
            )
        }
    }
}