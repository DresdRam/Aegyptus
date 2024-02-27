package sq.mayv.aegyptus.ui.screens.signup

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
import sq.mayv.aegyptus.dto.SignUpDto
import sq.mayv.aegyptus.model.Auth
import sq.mayv.aegyptus.repository.AuthRepository
import sq.mayv.aegyptus.util.PreferenceHelper.token
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferences: SharedPreferences
) :
    ViewModel() {

    private val _signUpData = MutableStateFlow(Resource<Auth>())
    val signUpData: StateFlow<Resource<Auth>> = _signUpData
    var isSignUpLoading by mutableStateOf(false)
    var isSignUpSuccessful by mutableStateOf(false)

    fun signUp(body: SignUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            isSignUpLoading = true

            _signUpData.value = authRepository.signUp(body)

            val statusCode = _signUpData.value.statusCode

            if (statusCode == 200 || statusCode == 201) {
                preferences.token = _signUpData.value.data!!.authorizationToken
                isSignUpSuccessful = true
            }

            isSignUpLoading = false
        }
    }
}
