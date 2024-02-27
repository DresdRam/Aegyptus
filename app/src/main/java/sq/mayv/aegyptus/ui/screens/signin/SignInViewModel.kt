package sq.mayv.aegyptus.ui.screens.signin

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
import sq.mayv.aegyptus.dto.SignInDto
import sq.mayv.aegyptus.model.Auth
import sq.mayv.aegyptus.repository.AuthRepository
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _signInData = MutableStateFlow(Resource<Auth>())
    val signInData: StateFlow<Resource<Auth>> = _signInData
    var isSignInLoading by mutableStateOf(false)
    var isSignInSuccessful by mutableStateOf(false)

    fun signIn(body: SignInDto) {
        viewModelScope.launch(Dispatchers.IO) {
            isSignInLoading = true

            _signInData.value = authRepository.signIn(body)

            val statusCode = _signInData.value.statusCode

            if (statusCode == 200 || statusCode == 201) {
                preferences.token = _signInData.value.data!!.authorizationToken
                isSignInSuccessful = true
            }

            isSignInLoading = false
        }
    }

}
