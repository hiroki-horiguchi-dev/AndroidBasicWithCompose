import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibians(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imsSrc: String
)