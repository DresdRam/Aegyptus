package sq.mayv.aegyptus.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.components.OutlinedRoundedButton
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.profile.components.OutlinedProfileButton
import sq.mayv.aegyptus.util.PreferenceHelper.token

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    rootNavController: NavController
) {

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(160.dp)
                    .border(
                        border = BorderStroke(
                            2.dp,
                            color = colorResource(id = R.color.primary)
                        ), shape = CircleShape
                    )
            ) {
                Image(
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.pharaoh),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Mahmoud Salah",
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                text = "test@test.com",
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )

            OutlinedProfileButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = "Profile"
            ) {

            }

            OutlinedProfileButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = "Settings"
            ) {

            }

            OutlinedProfileButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = "Privacy Policy"
            ) {

            }

            OutlinedRoundedButton(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = "Sign Out",
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White, contentColor = Color.Red
                ),
                borderColor = Color.Red,
                fontSize = 20
            ) {
                viewModel.preferences.token = ""
                // Using root nav controller to navigate in the root navhost.
                rootNavController.popBackStack()
                rootNavController.navigate(AppScreens.Auth.name)
            }
        }
    }
}