package sq.mayv.aegyptus.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import sq.mayv.aegyptus.dto.SignInDto
import sq.mayv.aegyptus.dto.SignUpDto
import sq.mayv.aegyptus.model.Auth
import sq.mayv.aegyptus.model.Place

interface Api {

    @GET("place/get-place")
    suspend fun getPlaceInfo(
        @Query("id") id: Int
    ): Response<Place>

    @GET("place/get-nearby")
    suspend fun getNearbyPlaces(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("governorate") governorate: Int,
        @Query("max_distance") maxDistance: Int = 1
    ): Response<List<Place>>

    @POST("user/sign-in")
    suspend fun signIn(
        @Body body: SignInDto
    ): Response<Auth>

    @POST("user/sign-up")
    suspend fun signUp(
        @Body body: SignUpDto
    ): Response<Auth>
}