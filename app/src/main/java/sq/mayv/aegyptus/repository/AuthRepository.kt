package sq.mayv.aegyptus.repository

import com.mayv.ctgate.data.Resource
import sq.mayv.aegyptus.dto.SignInDto
import sq.mayv.aegyptus.dto.SignUpDto
import sq.mayv.aegyptus.model.Auth
import sq.mayv.aegyptus.network.Api
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: Api) {

    suspend fun signIn(signInBody: SignInDto): Resource<Auth> {

        val authResource = Resource<Auth>()

        try {

            val response = api.signIn(signInBody)
            authResource.statusCode = response.code()

            if (response.isSuccessful) {
                authResource.data = response.body()
            } else {
                authResource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!") else if (response.code() == 409) Exception(
                        "This email does not exist"
                    ) else if (response.code() == 401) Exception("You have entered a wrong password") else if (response.code() == 400) Exception(
                        "Bad Request Exception"
                    ) else Exception(
                        "Internal Server Error"
                    )
            }

        } catch (exception: Exception) {
            authResource.exception = exception
            authResource.statusCode = 400
        }

        return authResource
    }

    suspend fun signUp(signUpBody: SignUpDto): Resource<Auth> {

        val authResource = Resource<Auth>()

        try {

            val response = api.signUp(signUpBody)
            authResource.statusCode = response.code()

            if (response.isSuccessful) {
                authResource.data = response.body()
            } else {
                authResource.exception =
                    if (response.code() == 404) Exception("There is no connection to the server!") else if (response.code() == 409) Exception(
                        "This email already in use"
                    ) else if (response.code() == 400) Exception(
                        "Bad Request Exception"
                    ) else Exception(
                        "Internal Server Error"
                    )
            }

        } catch (exception: Exception) {
            authResource.exception = exception
            authResource.statusCode = 400
        }

        return authResource
    }
}