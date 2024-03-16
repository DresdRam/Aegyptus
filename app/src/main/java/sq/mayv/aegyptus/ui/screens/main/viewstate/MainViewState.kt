package sq.mayv.aegyptus.ui.screens.main.viewstate

sealed interface MainViewState {
    data object CheckingPermissions : MainViewState
    data object GrantedPermissions : MainViewState
    data object RevokedPermissions : MainViewState
}