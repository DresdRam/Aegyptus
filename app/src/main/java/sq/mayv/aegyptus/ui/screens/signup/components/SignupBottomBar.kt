package sq.mayv.aegyptus.ui.screens.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sq.mayv.aegyptus.components.LoadingRoundedButton
import sq.mayv.aegyptus.components.OutlinedRoundedButton

@Composable
fun SignupBottomBar(
    isLoading: Boolean = false,
    onSignUpClick: () -> Unit,
    onLoginNowClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        LoadingRoundedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 35.dp),
            text = "Sign Up",
            fontSize = 18,
            isLoading = isLoading
        ) {
            onSignUpClick()
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.width(70.dp), color = Color.LightGray)

            Text(text = "or", modifier = Modifier.padding(horizontal = 10.dp))

            Divider(modifier = Modifier.width(70.dp), color = Color.LightGray)
        }

        OutlinedRoundedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 35.dp),
            text = "Already a member? Login now",
            fontSize = 14
        ) {
            onLoginNowClick()
        }
    }
}