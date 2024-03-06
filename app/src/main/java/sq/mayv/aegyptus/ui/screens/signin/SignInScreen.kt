package sq.mayv.aegyptus.ui.screens.signin

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
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
import sq.mayv.aegyptus.dto.SignInDto
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.signin.components.LoginBottomBar

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("test@test.com") }
    var password by remember { mutableStateOf("test123") }

    var emailMessage by remember { mutableStateOf("") }
    var passwordMessage by remember { mutableStateOf("") }

    var emailMessageVisibility by remember { mutableStateOf(false) }
    var passwordMessageVisibility by remember { mutableStateOf(false) }

    val data = viewModel.signInData.collectAsState().value

    var signUpClicked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = data.statusCode) {
        if (viewModel.isSignInSuccessful) {
            navController.popBackStack()
            navController.navigate(AppScreens.Main.name)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarTitleArrow(
                navController = navController,
                title = "Sign In",
                backArrowEnabled = false
            )
        },
        bottomBar = {
            LoginBottomBar(
                isLoading = viewModel.isSignInLoading,
                onSignInClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        if (!viewModel.isSignInLoading) {
                            if (email.isNotEmpty() || password.isNotEmpty()) {
                                val body = SignInDto(email = email, password = password)
                                viewModel.signIn(body = body)
                            } else {
                                if (email.isEmpty()) {
                                    emailMessage = "Please enter the email."
                                    emailMessageVisibility = true
                                }
                                if (password.isEmpty()) {
                                    passwordMessage = "Please enter the password."
                                    passwordMessageVisibility = true
                                }
                            }
                        }
                    }
                },
                onSignUpClick = {
                    if (!viewModel.isSignInLoading) {
                        if (!signUpClicked) {
                            signUpClicked = true
                            navController.navigate(AppScreens.SignUpScreen.name)
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 35.dp, top = 15.dp)
                    .clickable {
                        if (!viewModel.isSignInLoading) {
                            navController.navigate(AppScreens.RecoverPasswordScreen.name)
                        }
                    },
                text = "Forgot your password?",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.blue_clickable_text)
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