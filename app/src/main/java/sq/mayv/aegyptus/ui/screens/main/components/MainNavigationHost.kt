package sq.mayv.aegyptus.ui.screens.main.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.utsman.osmandcompose.MapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import com.utsman.osmandcompose.rememberOverlayManagerState
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.CopyrightOverlay
import sq.mayv.aegyptus.R
import sq.mayv.aegyptus.ui.screens.favorites.FavoritesScreen
import sq.mayv.aegyptus.ui.screens.home.HomeScreen
import sq.mayv.aegyptus.ui.screens.profile.ProfileScreen
import sq.mayv.aegyptus.util.BottomNavItem

@Composable
fun MainNavigationHost(rootNavController: NavController, navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen(rootNavController = rootNavController, navController = navController) }
        composable(BottomNavItem.Map.route) {

            val me =
                rememberMarkerState(geoPoint = GeoPoint(31.209723399356797, 29.882055618724696))


            val cameraState = rememberCameraState {
                geoPoint = me.geoPoint
                zoom = 18.0
            }

            val overlayManagerState = rememberOverlayManagerState()

            val context = LocalContext.current

            var meIcon: Drawable? by remember {
                mutableStateOf(context.getDrawable(R.drawable.ic_ankh_key))
            }

            var mapProperties by remember {
                mutableStateOf(
                    MapProperties(
                        isTilesScaledToDpi = true,
                        isEnableRotationGesture = true,
                        zoomButtonVisibility = ZoomButtonVisibility.NEVER
                    )
                )
            }

            OpenStreetMap(
                modifier = Modifier.fillMaxSize(),
                cameraState = cameraState,
                overlayManagerState = overlayManagerState,
                properties = mapProperties,
                onMapClick = { },
                onMapLongClick = {
                    me.geoPoint = it
                },
                onFirstLoadListener = {
                    overlayManagerState.overlayManager.add(CopyrightOverlay(context))
                }
            ) {
                Marker(
                    state = me,
                    icon = meIcon,
                    title = "Me",
                    snippet = "My location"
                ) {
                    Column(
                        modifier = Modifier
                            .size(100.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = it.title)
                        Text(text = it.snippet)
                    }
                }
            }
        }
        composable(BottomNavItem.Favorite.route) {
            FavoritesScreen(rootNavController = rootNavController, navController = navController)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(rootNavController = rootNavController, navController = navController)
        }
    }
}

//@Composable
//private fun HuaweiMap() {
//    val mapView = rememberMapViewWithLifecycle()
//
//    AndroidView({ mapView }) { mapView ->
//        CoroutineScope(Dispatchers.Main).launch {
//            mapView. { map ->
//                map.uiSettings.isZoomControlsEnabled = true
//
//                val rnd = LatLng(41.02959203350298, 29.117370221177943)
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(rnd, 6f))
//                val markerOptions = MarkerOptions()
//                    .title("Huawei RnD")
//                    .snippet("Huawei Turkey RnD Office")
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.business))
//                    .position(rnd)
//                map.addMarker(markerOptions)
//            }
//        }
//    }
//}