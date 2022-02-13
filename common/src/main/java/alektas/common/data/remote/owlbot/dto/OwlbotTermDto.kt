package alektas.common.data.remote.owlbot.dto

import com.google.gson.annotations.SerializedName

data class OwlbotTermDto(
    @SerializedName("word")
    val word: String,
    @SerializedName("pronunciation")
    val pronunciation: String? = null,
    @SerializedName("definitions")
    val definitions: List<OwlbotDefinitionDto> = listOf()
)
