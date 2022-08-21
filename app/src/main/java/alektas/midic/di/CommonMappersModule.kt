package alektas.midic.di

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.mappers.DefinitionItemMapper
import alektas.common.ui.mappers.TermItemMapper
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import dagger.Binds
import dagger.Module

@Module
interface CommonMappersModule {

    @Binds
    fun bindTermItemMapper(impl: TermItemMapper): DuplexMapper<Term, TermItem>

    @Binds
    fun bindDefinitionItemMapper(impl: DefinitionItemMapper): DuplexMapper<Definition, DefinitionItem>

}