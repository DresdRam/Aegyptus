package sq.mayv.aegyptus.ui.screens.home

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.model.Coordinates
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.repository.PlacesRepository
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val favoritesRepository: FavoritesRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _nearbyData = MutableStateFlow(Resource<List<Place>>())
    val nearbyData: StateFlow<Resource<List<Place>>> = _nearbyData
    var isNearbyLoading by mutableStateOf(true)
    var isNearbySuccessful by mutableStateOf(false)

    private val _mostVisitedData = MutableStateFlow(Resource<List<Place>>())
    val mostVisitedData: StateFlow<Resource<List<Place>>> = _mostVisitedData
    var isMostVisitedLoading by mutableStateOf(true)
    var isMostVisitedSuccessful by mutableStateOf(false)

    var isAddingFavorite by mutableStateOf(false)
    var addedSuccessfuly by mutableStateOf(false)
    var isRemovingFavorite by mutableStateOf(false)
    var removedSuccessfuly by mutableStateOf(false)

    fun getNearbyPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            if(!isNearbyLoading) {
                isNearbyLoading = true
            }

            _nearbyData.value =
                placesRepository.getNearbyPlaces(Coordinates(1.10, 11.110), preferences.token)

            val statusCode = _nearbyData.value.statusCode

            isNearbySuccessful = statusCode == 200 || statusCode == 201

            isNearbyLoading = false
        }
    }

    fun getMostVisitedPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            if(!isMostVisitedLoading) {
                isMostVisitedLoading = true
            }

            _mostVisitedData.value =
                placesRepository.getMostVisitedPlaces(preferences.token)

            val statusCode = mostVisitedData.value.statusCode

            isMostVisitedSuccessful = statusCode == 200 || statusCode == 201

            isMostVisitedLoading = false
        }
    }

    fun addToFavorites(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isAddingFavorite = true

            val value = favoritesRepository.addToFavorite(placeId, preferences.token)

            val statusCode = value.statusCode

            addedSuccessfuly = statusCode == 200 || statusCode == 201

            isAddingFavorite = false
        }
    }

    fun removeFromFavorites(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isRemovingFavorite = true

            val value = favoritesRepository.removeFromFavorites(placeId, preferences.token)

            val statusCode = value.statusCode

            removedSuccessfuly = statusCode == 200 || statusCode == 201

            isRemovingFavorite = false
        }
    }

}
