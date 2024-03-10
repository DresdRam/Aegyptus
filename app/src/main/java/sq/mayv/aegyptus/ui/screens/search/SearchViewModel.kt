package sq.mayv.aegyptus.ui.screens.search

import android.content.SharedPreferences
import android.util.Log
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
import retrofit2.http.Query
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.repository.PlacesRepository
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val favoritesRepository: FavoritesRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _placesData = MutableStateFlow(Resource<List<Place>>())
    val placesData: StateFlow<Resource<List<Place>>> = _placesData
    var isLoading by mutableStateOf(true)
    var isSuccessful by mutableStateOf(false)

    var isAddingFavorite by mutableStateOf(false)
    var addedSuccessfuly by mutableStateOf(false)
    var isRemovingFavorite by mutableStateOf(false)
    var removedSuccessfuly by mutableStateOf(false)

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(!isLoading) {
                isLoading = true
            }

            _placesData.value =
                placesRepository.search(query = query, authToken = preferences.token)


            val statusCode = _placesData.value.statusCode

            isSuccessful = statusCode == 200 || statusCode == 201

            isLoading = false
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
