package sq.mayv.aegyptus.repository

import com.mayv.ctgate.data.Resource
import sq.mayv.aegyptus.model.Category
import sq.mayv.aegyptus.network.Api
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getAll(): Resource<List<Category>> {

        val placeResource = Resource<List<Category>>()

        try {

            val response = api.getAllCategories()
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
}