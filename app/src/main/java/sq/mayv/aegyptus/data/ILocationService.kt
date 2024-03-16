package sq.mayv.aegyptus.data

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ILocationService {

    fun requestLocationUpdates(): Flow<LatLng?>

}