package sq.mayv.aegyptus.model

import com.google.gson.annotations.SerializedName

data class Place(
    val id: Int = 0,
    val name: String = "",
    val about: String = "",
    val address: String = "",
    val coordinates: String = "",
    val governorate: Governorate = Governorate(),
    val category: Category = Category(),
    val location: String = "",
    val ticket: String = "",
    val time: String = "",
    val thumbnail: String = "",
    val images: String = "",
    @SerializedName("is_favorite") val isFavorite: Boolean = false,
    @SerializedName("distance_in_meters") val distanceInMeters: Int = -1
)