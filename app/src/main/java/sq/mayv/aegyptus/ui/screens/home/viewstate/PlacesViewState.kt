package sq.mayv.aegyptus.ui.screens.home.viewstate

import sq.mayv.aegyptus.model.Place

sealed interface PlacesViewState {
    data object Loading : PlacesViewState
    data class Success(val places: List<Place>) : PlacesViewState
    data object Failure : PlacesViewState
}