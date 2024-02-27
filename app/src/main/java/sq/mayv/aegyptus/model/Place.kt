package sq.mayv.aegyptus.model

data class Place(
    val id: Int = 0,
    val name: String = "",
    val about: String = "",
    val address: String = "",
    val coordinates: String = "",
    val governorate: Governorate = Governorate(),
    val location: String = "",
    val ticket: String = "",
    val time: String = "",
    val images: String = "",
    val distanceInMeters: Int = -1
)