package sq.mayv.aegyptus.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import sq.mayv.aegyptus.data.PermissionEvent
import sq.mayv.aegyptus.ui.screens.main.viewstate.MainViewState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _viewState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState.CheckingPermissions)

    val viewState = _viewState.asStateFlow()

    fun handleViewState(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                _viewState.value = MainViewState.GrantedPermissions
            }

            PermissionEvent.Revoked -> {
                _viewState.value = MainViewState.RevokedPermissions
            }
        }
    }
}