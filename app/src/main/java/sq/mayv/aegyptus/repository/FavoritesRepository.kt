package sq.mayv.aegyptus.repository

import com.mayv.ctgate.data.Resource
import sq.mayv.aegyptus.dto.SavePlace
import sq.mayv.aegyptus.model.Place
import sq.mayv.aegyptus.model.Response
import sq.mayv.aegyptus.network.Api
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getAllFavorites(authToken: String): Resource<List<Place>> {

        val resource = Resource<List<Place>>()

        try {

            val response = api.getAllFavorites(authToken = authToken)
            resource.statusCode = response.code()

            if (response.isSuccessful) {
                resource.data = response.body()
            } else {
                resource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            resource.exception = exception
            resource.statusCode = 400
        }

        return resource
    }

    suspend fun addToFavorite(placeId: Int, authToken: String): Resource<Response> {

        val resource = Resource<Response>()

        try {

            val response = api.addToFavorites(body = SavePlace(placeId), authToken = authToken)
            resource.statusCode = response.code()

            if (response.isSuccessful) {
                resource.data = response.body()
            } else {
                resource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            resource.exception = exception
            resource.statusCode = 400
        }

        return resource
    }

    suspend fun removeFromFavorites(placeId: Int, authToken: String): Resource<Response> {

        val resource = Resource<Response>()

        try {

            val response = api.removeFromFavorites(placeId = placeId, authToken = authToken)
            resource.statusCode = response.code()

            if (response.isSuccessful) {
                resource.data = response.body()
            } else {
                resource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!")
                    else if (response.code() == 409) Exception("Unauthorized Connection")
                    else if (response.code() == 401) Exception("You are not authorized")
                    else if (response.code() == 400) Exception("Bad Request Exception")
                    else Exception("Internal Server Error")
            }

        } catch (exception: Exception) {
            resource.exception = exception
            resource.statusCode = 400
        }

        return resource
    }
}