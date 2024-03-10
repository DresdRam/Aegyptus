package sq.mayv.aegyptus.ui.screens.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.components.SearchTextField

@Composable
fun SearchTopBar(
    navController: NavController,
    searchQuery: String,
    onSearchClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(end = 20.dp)
            .fillMaxWidth()
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back Arrow",
                tint = Color.Black
            )
        }

        var searchQuery by remember { mutableStateOf(searchQuery) }
        var trailingIconVisibility by remember { mutableStateOf(true) }

        SearchTextField(
            modifier = Modifier
                .fillMaxWidth(),
            search = searchQuery,
            trailingIconVisibility = trailingIconVisibility,
            onValueChange = { value ->
                searchQuery = value
                if (searchQuery.isNotEmpty()) {
                    if (!trailingIconVisibility) {
                        trailingIconVisibility = true
                    }
                } else {
                    trailingIconVisibility = false
                }
            },
            onSearchClick = {
                onSearchClick(searchQuery)
            },
            onTrailingIconClick = {
                searchQuery = ""
                trailingIconVisibility = false
            }
        )
    }
}