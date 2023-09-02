package pet.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    @SerialName("declawed")
    val declawed: Boolean = false,
    @SerialName("house_trained")
    val houseTrained: Boolean = false,
    @SerialName("shots_current")
    val shotsCurrent: Boolean = false,
    @SerialName("spayed_neutered")
    val spayedNeutered: Boolean = false,
    @SerialName("special_needs")
    val specialNeeds: Boolean = false
)