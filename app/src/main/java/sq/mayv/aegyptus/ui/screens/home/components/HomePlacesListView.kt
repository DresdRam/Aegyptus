package sq.mayv.aegyptus.ui.screens.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import sq.mayv.aegyptus.components.OutlinedMessageView
import sq.mayv.aegyptus.model.Place

@Composable
fun HomePlacesListView(
    places: List<Place>,
    emptyMessage: String,
    onItemClick: (Int) -> Unit,
    onSaveClick: (Int, Boolean) -> Unit
) {
    AnimatedContent(
        targetState = places.isEmpty(),
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
    ) {isEmpty ->
        if(!isEmpty) {
            LazyRow {
                items(items = places, key = { it.id }) { place ->
                    HomePlacesListItem(
                        place = place,
                        onItemClick = { onItemClick(it) },
                        onSaveClick = { id, isFavorite ->
                            place.isFavorite = !place.isFavorite
                            onSaveClick(id, isFavorite)
                        }
                    )
                }
            }
        } else {
            OutlinedMessageView(message = emptyMessage)
        }
    }
}