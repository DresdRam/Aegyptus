package sq.mayv.aegyptus.ui.screens.signup

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.components.PasswordTextInputField
import sq.mayv.aegyptus.components.TextInputField
import sq.mayv.aegyptus.components.TopBarTitleArrow
import sq.mayv.aegyptus.dto.SignUpDto
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.signin.components.SignupBottomBar
import java.util.regex.Pattern

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var nameMessage by remember { mutableStateOf("Please enter your name") }
    var nameMessageVisibility by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailMessage by remember { mutableStateOf("Please enter an email") }
    var emailMessageVisibility by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordMessage by remember { mutableStateOf("Please enter a password") }
    var passwordMessageVisibility by remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordMessage by remember { mutableStateOf("Please confirm your password ") }
    var confirmPasswordMessageVisibility by remember { mutableStateOf(false) }

    val data by viewModel.signUpData.collectAsState()

    var signInClicked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = data.statusCode) {
        if (viewModel.isSignUpSuccessful) {
            navController.popBackStack()
            navController.navigate(AppScreens.Main.name)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarTitleArrow(
                navController = navController,
                title = "Sign Up",
            )
        },
        bottomBar = {
            SignupBottomBar(
                isLoading = viewModel.isSignUpLoading,
                onSignUpClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        if (!viewModel.isSignUpLoading) {
                            if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || confirmPassword.isNotEmpty()) {
                                if (!isEmailValid(email) || !isPasswordValid(password)) {
                                    if (!isEmailValid(email)) {
                                        emailMessage = "The email you entered is not valid"
                                        emailMessageVisibility = true
                                    }
                                    if (!isPasswordValid(password)) {
                                        passwordMessage =
                                            "Must have at least one uppercase character.\nMust have at least one number.\nMust be at least 8 characters."
                                        passwordMessageVisibility = true
                                    }
                                } else {
                                    if (password != confirmPassword) {
                                        confirmPasswordMessage = "Passwords do not match"
                                        confirmPasswordMessageVisibility = true
                                    } else {
                                        val body =
                                            SignUpDto(
                                                name = name,
                                                email = email,
                                                password = password
                                            )
                                        viewModel.signUp(body = body)
                                    }
                                }
                            } else {
                                if (name.isEmpty()) {
                                    nameMessageVisibility = true
                                }
                                if (email.isEmpty()) {
                                    emailMessageVisibility = true
                                }
                                if (password.isEmpty()) {
                                    passwordMessageVisibility = true
                                }
                                if (confirmPassword.isEmpty()) {
                                    confirmPasswordMessageVisibility = true
                                }
                            }
                        }
                    }
                },
                onLoginNowClick = {
                    if (!viewModel.isSignUpLoading) {
                        if (!signInClicked) {
                            signInClicked = true
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(bottom = 35.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 15.dp),
                value = name,
                onValueChange = { value ->
                    name = value
                    if (nameMessage.isNotEmpty()) {
                        nameMessageVisibility = value == ""
                    }
                },
                label = "Name",
                message = nameMessage,
                messageModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp),
                messageVisibility = nameMessageVisibility,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false
                )
            )

            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 15.dp),
                value = email,
                onValueChange = { value ->
                    email = value
                    if (emailMessage.isNotEmpty()) {
                        emailMessageVisibility = value == ""
                    }
                },
                label = "Email",
                message = emailMessage,
                messageModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp),
                messageVisibility = emailMessageVisibility,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false
                )
            )

            PasswordTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 15.dp),
                value = password,
                onValueChange = { value ->
                    password = value
                    if (passwordMessage.isNotEmpty()) {
                        passwordMessageVisibility = value == ""
                    }
                },
                label = "Password",
                message = passwordMessage,
                errorModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp),
                messageVisibility = passwordMessageVisibility
            )

            PasswordTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 15.dp),
                value = confirmPassword,
                onValueChange = { value ->
                    confirmPassword = value
                    if (confirmPasswordMessage.isNotEmpty()) {
                        confirmPasswordMessageVisibility = value == ""
                    }
                },
                label = "Confirm Password",
                message = confirmPasswordMessage,
                errorModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp),
                messageVisibility = confirmPasswordMessageVisibility
            )

            Text(
                text = if (data.statusCode in (400..500)) data.exception.message.toString() else "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
                    .padding(vertical = 25.dp)
                    .alpha(if (data.statusCode != 200 || data.statusCode != 201) 1f else 0f),
                color = colorResource(id = R.color.gold),
                textAlign = TextAlign.Center
            )

        }
    }
}


private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isPasswordValid(password: String): Boolean {
    // Must have at least one uppercase character.
    // Must have at least one number.
    // Must be at least 8 characters.
    return Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
        .matcher(password).matches()
}