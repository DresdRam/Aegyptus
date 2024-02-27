package sq.mayv.aegyptus.model

import com.google.gson.annotations.SerializedName

data class Auth(
    val email: String = "",
    val name: String = "",
    @SerializedName("authorization_token") val authorizationToken: String = ""
)