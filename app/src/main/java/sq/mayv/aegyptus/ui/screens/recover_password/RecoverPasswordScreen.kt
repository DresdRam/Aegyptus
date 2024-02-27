package sq.mayv.aegyptus.ui.screens.recover_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.components.LoadingRoundedButton
import sq.mayv.aegyptus.components.TextInputField
import sq.mayv.aegyptus.components.TopBarTitleArrow

@Composable
fun RecoverPasswordScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarTitleArrow(
                navController = navController,
                title = "Recover Password"
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoadingRoundedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(horizontal = 35.dp),
                    text = "Confirm",
                    fontSize = 18,
                    isLoading = isLoading
                ) {
                    coroutineScope.launch {
                        isLoading = true
                        delay(4000)
                        navController.popBackStack()
                    }
                }
            }
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var email by remember { mutableStateOf("") }
            var emailMessageVisibility by remember { mutableStateOf(false) }

            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 25.dp),
                value = email,
                onValueChange = { value ->
                    email = value
                    emailMessageVisibility = value != ""
                },
                label = "Email",
                message = "If this email exists in our database you will receive a password resetting link immediately.",
                messageModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp),
                messageVisibility = emailMessageVisibility,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false
                )
            )
        }
    }
}