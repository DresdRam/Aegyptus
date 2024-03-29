package sq.mayv.aegyptus.ui.screens.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    val preferences: SharedPreferences
) :
    ViewModel() {
}
