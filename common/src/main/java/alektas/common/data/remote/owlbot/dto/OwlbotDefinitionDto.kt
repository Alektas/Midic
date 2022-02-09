package alektas.common.data.remote.owlbot.dto

import com.google.gson.annotations.SerializedName

data class OwlbotDefinitionDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("emoji")
    val emoji: String? = null,
)
