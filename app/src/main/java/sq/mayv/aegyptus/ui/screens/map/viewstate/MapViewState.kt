package sq.mayv.aegyptus.ui.screens.map.viewstate

import com.google.maps.android.compose.MapType
import sq.mayv.aegyptus.model.Place

sealed interface MapViewState {
    data object Loading : MapViewState
    data class Success(
        val places: List<Place>,
        val mapType: MapType,
        val trafficMode: Boolean,
        ) : MapViewState
    data object Failure : MapViewState
}