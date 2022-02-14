package alektas.term_details.domain

import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow

interface TermDetailsRepository {

    fun observeTerm(): StateFlow<Term?>

    fun saveToBookmarks(term: Term)

}