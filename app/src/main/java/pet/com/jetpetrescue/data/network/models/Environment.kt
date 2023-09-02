package pet.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Environment(
    @SerialName("cats")
    val cats: Boolean = false,
    @SerialName("children")
    val children: Boolean = false,
    @SerialName("dogs")
    val dogs: Boolean = false
)