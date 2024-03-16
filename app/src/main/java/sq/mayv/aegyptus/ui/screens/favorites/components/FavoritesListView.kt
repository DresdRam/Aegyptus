package sq.mayv.aegyptus.ui.screens.favorites.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.components.MessageView
import sq.mayv.aegyptus.model.Place

@Composable
fun FavoritesListView(
    favorites: List<Place>,
    onItemClick: (Int) -> Unit
) {
    AnimatedContent(
        targetState = favorites.isEmpty(),
        label = "",
        transitionSpec = {
            fadeIn(
                animationSpec = tween(600, easing = EaseIn)
            ).togetherWith(
                fadeOut(
                    animationSpec = tween(600, easing = EaseOut)
                )
            )
        }
    ) { isEmpty ->
        if (!isEmpty) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(top = 15.dp, bottom = 90.dp),
            ) {
                items(favorites, key = { it.id }) { favorite ->
                    FavoritesListItem(
                        favorite = favorite,
                        onItemClick = { onItemClick(it) }
                    )
                }
            }
        } else {
            MessageView(message = "You haven't added favorites yet.")
        }
    }
}