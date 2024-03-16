package sq.mayv.aegyptus.repository

import com.mayv.ctgate.data.Resource
import sq.mayv.aegyptus.model.Coordinates
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.network.Api
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getPlace(placeId: Int, authToken: String): Resource<Place> {

        val placeResource = Resource<Place>()

        try {

            val response = api.getPlaceInfo(id = placeId, authToken = authToken)
            placeResource.statusCode = response.code()

            if (response.isSuccessful) {
                placeResource.data = response.body()
            } else {
                placeResource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            placeResource.exception = exception
            placeResource.statusCode = 400
        }

        return placeResource
    }

    suspend fun getAllPlaces(governorateCode: Int, authToken: String): Resource<List<Place>> {

        val placeResource = Resource<List<Place>>()

        try {

            val response = api.getAllPlaces(governorate = governorateCode, authToken = authToken)
            placeResource.statusCode = response.code()

            if (response.isSuccessful) {
                placeResource.data = response.body()
            } else {
                placeResource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            placeResource.exception = exception
            placeResource.statusCode = 400
        }

        return placeResource
    }

    suspend fun getNearbyPlaces(
        coordinates: Coordinates,
        maxDistance: Int,
        authToken: String
    ): Resource<List<Place>> {

        val placesResource = Resource<List<Place>>()

        try {

            val response = api.getNearbyPlaces(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude,
                maxDistance = maxDistance,
                authToken = authToken
            )
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

    suspend fun getMostVisitedPlaces(authToken: String): Resource<List<Place>> {

        val placesResource = Resource<List<Place>>()

        try {

            val response = api.getMostVisitedPlaces(governorateCode = 1, authToken = authToken)
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

    suspend fun search(query: String, authToken: String): Resource<List<Place>> {

        val placesResource = Resource<List<Place>>()

        try {

            val response = api.search(query = query, authToken = authToken)
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