package sq.mayv.aegyptus.ui.screens.map.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import sq.mayv.aegyptus.extension.centerOnLocation
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.ui.navigation.AppScreens
import sq.mayv.aegyptus.ui.screens.map.MapViewModel

@Composable
fun Map(
    rootNavController: NavController,
    viewModel: MapViewModel,
    places: List<Place>,
    mapType: MapType,
    trafficMode: Boolean
) {
    val cameraState = rememberCameraPositionState()
    val currentLocation by viewModel.currentLocation.collectAsState()

    LaunchedEffect(key1 = currentLocation) {
        cameraState.centerOnLocation(currentLocation)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraState,
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapType = mapType,
            isTrafficEnabled = trafficMode
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        )
    ) {
        places.forEach { place ->
            val latLng = place.coordinates.split(',')
            Marker(
                state = MarkerState(position = LatLng(latLng[0].toDouble(), latLng[1].toDouble())),
                title = place.name,
                snippet = "Click To View",
                draggable = false,
                onInfoWindowClick = {
                    rootNavController.navigate(AppScreens.PlaceScreen.name.plus(place.id))
                }
            )
        }
    }
}