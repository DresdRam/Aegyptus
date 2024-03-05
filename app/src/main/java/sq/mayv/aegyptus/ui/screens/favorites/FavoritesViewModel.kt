package sq.mayv.aegyptus.ui.screens.favorites

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
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _favoritesData = MutableStateFlow(Resource<List<Place>>())
    val favoritesData: StateFlow<Resource<List<Place>>> = _favoritesData
    var isFavoritesLoading by mutableStateOf(false)
    var isSuccessful by mutableStateOf(false)

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            isFavoritesLoading = true

            _favoritesData.value =
                favoritesRepository.getAllFavorites(preferences.token)

            val statusCode = _favoritesData.value.statusCode

            isSuccessful = statusCode == 200 || statusCode == 201

            isFavoritesLoading = false
        }
    }

}
