package sq.mayv.aegyptus.ui.screens.home.viewstate

import sq.mayv.aegyptus.util.CategoryItem

sealed interface CategoriesViewState {
    data object Loading : CategoriesViewState
    data class Success(val categories: List<CategoryItem>) : CategoriesViewState
    data object Failure : CategoriesViewState
}