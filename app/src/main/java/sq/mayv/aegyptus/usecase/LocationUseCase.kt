package sq.mayv.aegyptus.usecase

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import sq.mayv.aegyptus.data.ILocationService
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}