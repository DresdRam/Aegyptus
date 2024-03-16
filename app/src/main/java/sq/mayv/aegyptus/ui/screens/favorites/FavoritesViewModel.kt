package sq.mayv.aegyptus.ui.screens.favorites

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.ui.screens.favorites.viewstate.FavoritesViewState
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _viewState: MutableStateFlow<FavoritesViewState> =
        MutableStateFlow(FavoritesViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _favoritesData = MutableStateFlow(Resource<List<Place>>())

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_viewState.value != FavoritesViewState.Loading) {
                _viewState.value = FavoritesViewState.Loading
            }

            _favoritesData.value =
                favoritesRepository.getAllFavorites(preferences.token)

            val statusCode = _favoritesData.value.statusCode
            val isSuccessful = statusCode == 200 || statusCode == 201

            if(!isSuccessful) {
                _viewState.value = FavoritesViewState.Failure
            } else {
                _viewState.value = FavoritesViewState.Success(_favoritesData.value.data ?: listOf())
            }
        }
    }

}
