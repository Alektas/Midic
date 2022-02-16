package alektas.term_search.data.remote

import alektas.common.domain.Definition
import alektas.common.domain.Term
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeRemoteTermSearchSource @Inject constructor(): RemoteTermSearchSource {

    override suspend fun queryTerms(query: String): List<Term> = buildList {
        delay(1_000)
        repeat(50) {
            val item = Term(
                id = it.toLong(),
                word = "$query $it",
                transcription = "/$query $it/",
                definitions = buildList {
                    repeat(it) {
                        add(Definition(it.toLong(), "noun", "Definition", inBookmarks = false))
                    }
                }
            )
            add(item)
        }
    }

}