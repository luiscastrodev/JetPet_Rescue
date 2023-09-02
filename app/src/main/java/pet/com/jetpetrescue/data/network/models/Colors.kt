package pet.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    @SerialName("primary")
    val primary: String = "",
    @SerialName("secondary")
    val secondary:  String = "",
    @SerialName("tertiary")
    val tertiary:  String = ""
)