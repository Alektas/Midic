package alektas.term_search.di

import alektas.common.data.local.in_memory.SelectedTermCacheInput
import alektas.common.data.remote.owlbot.OwlbotApi

interface TermSearchDependencies {

    fun owlbotApi(): OwlbotApi

    fun selectedTermInput(): SelectedTermCacheInput

}