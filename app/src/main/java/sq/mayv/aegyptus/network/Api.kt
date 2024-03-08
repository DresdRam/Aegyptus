package sq.mayv.aegyptus.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import sq.mayv.aegyptus.dto.SavePlace
import sq.mayv.aegyptus.dto.SignInDto
import sq.mayv.aegyptus.dto.SignUpDto
import sq.mayv.aegyptus.model.Auth
import sq.mayv.aegyptus.model.Place

interface Api {

    @GET("place/get-one")
    suspend fun getPlaceInfo(
        @Query("id") id: Int,
        @Header("Authorization") authToken: String
    ): Response<Place>

    @GET("favorite/get-all")
    suspend fun getAllFavorites(
        @Header("Authorization") authToken: String
    ): Response<List<Place>>

    @GET("place/get-nearby")
    suspend fun getNearbyPlaces(
        @Query("latitude") latitude: Double = 31.209723399356797,
        @Query("longitude") longitude: Double = 29.882055618724696,
        @Query("governorate") governorate: Int = 4,
        @Query("max_distance") maxDistance: Int = 1,
        @Header("Authorization") authToken: String
    ): Response<List<Place>>

    @POST("favorite/create")
    suspend fun addToFavorites(
        @Body body: SavePlace,
        @Header("Authorization") authToken: String
    ): Response<sq.mayv.aegyptus.model.Response>

    @DELETE("favorite/remove")
    suspend fun removeFromFavorites(
        @Query("place_id") placeId: Int,
        @Header("Authorization") authToken: String
    ): Response<sq.mayv.aegyptus.model.Response>

    @POST("user/sign-in")
    suspend fun signIn(
        @Body body: SignInDto
    ): Response<Auth>

    @POST("user/sign-up")
    suspend fun signUp(
        @Body body: SignUpDto
    ): Response<Auth>
}