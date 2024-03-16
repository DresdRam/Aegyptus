package sq.mayv.aegyptus.ui.screens.map

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapType
import com.mayv.ctgate.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.PlacesRepository
import sq.mayv.aegyptus.ui.screens.map.viewstate.MapViewState
import sq.mayv.aegyptus.usecase.LocationUseCase
import sq.mayv.aegyptus.util.PreferenceHelper.mapType
import sq.mayv.aegyptus.util.PreferenceHelper.token
import sq.mayv.aegyptus.util.PreferenceHelper.trafficMode
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val locationUseCase: LocationUseCase,
    private val preferences: SharedPreferences
) : ViewModel() {

    private val _placesData = MutableStateFlow(Resource<List<Place>>())

    private val _viewState: MutableStateFlow<MapViewState> = MutableStateFlow(MapViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _currentLocation = MutableStateFlow(LatLng(0.00, 0.00))
    val currentLocation: StateFlow<LatLng> = _currentLocation

    fun getAllPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_viewState.value != MapViewState.Loading) {
                _viewState.value = MapViewState.Loading
            }

            locationUseCase.invoke().collect { latLng ->
                _placesData.value =
                    placesRepository.getAllPlaces(
                        governorateCode = 1,
                        authToken = preferences.token
                    )

                val statusCode = _placesData.value.statusCode

                val isPlacesSuccessful = statusCode == 200 || statusCode == 201

                if (!isPlacesSuccessful) {
                    _viewState.value = MapViewState.Failure
                } else {
                    _currentLocation.value = latLng ?: LatLng(0.00, 0.00)

                    _viewState.value = MapViewState.Success(
                        places = _placesData.value.data ?: listOf(),
                        mapType = getMapType(preferences.mapType),
                        trafficMode = preferences.trafficMode
                    )
                }
            }
        }
    }

    private fun getMapType(id: Int): MapType {
        return when(id) {
            0 -> MapType.NONE
            1 -> MapType.NORMAL
            2 -> MapType.SATELLITE
            3 -> MapType.TERRAIN
            4 -> MapType.HYBRID
            else -> MapType.NORMAL
        }
    }
}