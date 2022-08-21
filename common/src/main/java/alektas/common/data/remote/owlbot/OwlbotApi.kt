package alektas.common.data.remote.owlbot

import alektas.common.data.remote.owlbot.dto.OwlbotTermDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OwlbotApi {

    @GET("dictionary/{word}")
    suspend fun queryTerms(@Path("word") query: String): OwlbotTermDto

}