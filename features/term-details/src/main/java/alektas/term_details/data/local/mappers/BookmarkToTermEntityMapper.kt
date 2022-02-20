package alektas.term_details.data.local.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.domain.Bookmark
import javax.inject.Inject

class BookmarkToTermEntityMapper @Inject constructor() : Mapper<Bookmark, TermEntity> {

    override fun map(input: Bookmark): TermEntity = with(input) {
        TermEntity(
            word = word,
            pronunciation = transcription,
        )
    }

}
