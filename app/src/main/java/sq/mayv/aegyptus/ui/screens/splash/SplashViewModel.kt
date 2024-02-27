package sq.mayv.aegyptus.ui.screens.splash

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(preferences: SharedPreferences) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> =
        mutableStateOf(AppScreens.WelcomeScreen.name)
    val startDestination: State<String> = _startDestination

    init {
        if (preferences.token.isNotEmpty()) {
            _startDestination.value = AppScreens.Main.name
        }

        _isLoading.value = false
    }

}