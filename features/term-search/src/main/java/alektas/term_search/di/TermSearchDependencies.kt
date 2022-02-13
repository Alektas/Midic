package alektas.term_search.di

import alektas.common.data.remote.owlbot.OwlbotApi

interface TermSearchDependencies {

    fun owlbotApi(): OwlbotApi

}