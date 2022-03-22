package alektas.term_details.ui.models

sealed interface Event {

    data class CopyToClipboard(val text: String) : Event

}
