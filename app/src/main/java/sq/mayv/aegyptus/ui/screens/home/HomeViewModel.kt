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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.model.Category
import sq.mayv.aegyptus.model.Coordinates
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.CategoriesRepository
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.repository.PlacesRepository
import sq.mayv.aegyptus.ui.screens.home.viewstate.CategoriesViewState
import sq.mayv.aegyptus.ui.screens.home.viewstate.PlacesViewState
import sq.mayv.aegyptus.usecase.LocationUseCase
import sq.mayv.aegyptus.util.CategoryItem
import sq.mayv.aegyptus.util.PreferenceHelper.nearbyThreshold
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val favoritesRepository: FavoritesRepository,
    private val categoriesRepository: CategoriesRepository,
    private val preferences: SharedPreferences,
    private val locationUseCase: LocationUseCase
) :
    ViewModel() {

    private val _nearbyViewState: MutableStateFlow<PlacesViewState> =
        MutableStateFlow(PlacesViewState.Loading)
    val nearbyViewState = _nearbyViewState.asStateFlow()

    private val _mostVisitedViewState: MutableStateFlow<PlacesViewState> =
        MutableStateFlow(PlacesViewState.Loading)
    val mostVisitedViewState = _mostVisitedViewState.asStateFlow()

    private val _categoriesViewState: MutableStateFlow<CategoriesViewState> =
        MutableStateFlow(CategoriesViewState.Loading)
    val categoriesViewState = _categoriesViewState.asStateFlow()

    private val _nearbyData = MutableStateFlow(Resource<List<Place>>())
    private val _mostVisitedData = MutableStateFlow(Resource<List<Place>>())
    private val _categoriesData = MutableStateFlow(Resource<List<Category>>())

    var isAddingFavorite by mutableStateOf(false)
    var addedSuccessfuly by mutableStateOf(false)
    var isRemovingFavorite by mutableStateOf(false)
    var removedSuccessfuly by mutableStateOf(false)

    fun getNearbyPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_nearbyViewState.value != PlacesViewState.Loading) {
                _nearbyViewState.value = PlacesViewState.Loading
            }

            locationUseCase.invoke().collect { latLng ->
                if (_nearbyViewState.value != PlacesViewState.Loading) {
                    _nearbyViewState.value = PlacesViewState.Loading
                }

                _nearbyData.value =
                    placesRepository.getNearbyPlaces(
                        coordinates = Coordinates(latLng?.latitude ?: 0.00, latLng?.longitude ?: 0.00),
                        maxDistance = preferences.nearbyThreshold,
                        authToken = preferences.token
                    )

                val statusCode = _nearbyData.value.statusCode
                val isNearbySuccessful = statusCode == 200 || statusCode == 201

                if (!isNearbySuccessful) {
                    _nearbyViewState.value = PlacesViewState.Failure
                } else {
                    _nearbyViewState.value =
                        PlacesViewState.Success(_nearbyData.value.data ?: listOf())
                }
            }
        }
    }

    fun getMostVisitedPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_mostVisitedViewState.value != PlacesViewState.Loading) {
                _mostVisitedViewState.value = PlacesViewState.Loading
            }

            _mostVisitedData.value =
                placesRepository.getMostVisitedPlaces(preferences.token)

            val statusCode = _mostVisitedData.value.statusCode
            val isMostVisitedSuccessful = statusCode == 200 || statusCode == 201

            if (!isMostVisitedSuccessful) {
                _mostVisitedViewState.value = PlacesViewState.Failure
            } else {
                _mostVisitedViewState.value =
                    PlacesViewState.Success(_mostVisitedData.value.data ?: listOf())
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_categoriesViewState.value != CategoriesViewState.Loading) {
                _categoriesViewState.value = CategoriesViewState.Loading
            }

            _categoriesData.value =
                categoriesRepository.getAll()

            val statusCode = _categoriesData.value.statusCode
            val isCategoriesSuccessful = statusCode == 200 || statusCode == 201

            if (!isCategoriesSuccessful) {
                _categoriesViewState.value = CategoriesViewState.Failure
            } else {
                _categoriesViewState.value =
                    CategoriesViewState.Success(
                        CategoryItem.getCategoryItems(
                            _categoriesData.value.data ?: listOf()
                        )
                    )
            }
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