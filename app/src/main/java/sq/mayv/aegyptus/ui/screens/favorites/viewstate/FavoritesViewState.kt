package sq.mayv.aegyptus.ui.screens.favorites.viewstate

import sq.mayv.aegyptus.model.Place

sealed interface FavoritesViewState {
    data object Loading : FavoritesViewState
    data class Success(val places: List<Place>) : FavoritesViewState
    data object Failure : FavoritesViewState
}