package sq.mayv.aegyptus.repository

import com.mayv.ctgate.data.Resource
import sq.mayv.aegyptus.model.Coordinates
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.network.Api
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getNearbyPlaces(coordinates: Coordinates, authToken: String): Resource<List<Place>> {

        val placesResource = Resource<List<Place>>()

        try {

            val response = api.getNearbyPlaces(authToken = authToken)
            placesResource.statusCode = response.code()

            if (response.isSuccessful) {
                placesResource.data = response.body()
            } else {
                placesResource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            placesResource.exception = exception
            placesResource.statusCode = 400
        }

        return placesResource
    }
}