package alektas.term_details.di

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.data.local.in_memory.term_search_events.TermSearchEventSourceInput
import alektas.common.data.local.in_memory.term_selection.SelectedTermCache
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem

interface TermDetailsDependencies {

    fun selectedTerm(): SelectedTermCache

    fun termSearchEventSourceInput(): TermSearchEventSourceInput

    fun bookmarkDao(): BookmarksDao

    fun termItemMapper(): DuplexMapper<Term, TermItem>

    fun definitionItemMapper(): DuplexMapper<Definition, DefinitionItem>

}
