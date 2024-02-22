package sq.mayv.aegyptus.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("get-place")
    fun getPlaceInfo(@Query("id") id: Int): ResponseBody

}