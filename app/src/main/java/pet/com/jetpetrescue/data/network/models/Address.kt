package pet.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("address1")
    val address1: String = "",
    @SerialName("address2")
    val address2: String = "",
    @SerialName("city")
    val city: String = "",
    @SerialName("country")
    val country: String = "",
    @SerialName("postcode")
    val postcode: String = "",
    @SerialName("state")
    val state: String = ""
)