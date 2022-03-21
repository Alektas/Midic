package alektas.term_search.di

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.data.local.in_memory.term_selection.SelectedTermCacheInput
import alektas.common.data.remote.owlbot.OwlbotApi
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem

interface TermSearchDependencies {

    fun owlbotApi(): OwlbotApi

    fun selectedTermInput(): SelectedTermCacheInput

    fun termItemMapper(): DuplexMapper<Term, TermItem>

    fun definitionItemMapper(): DuplexMapper<Definition, DefinitionItem>

}