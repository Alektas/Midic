package alektas.term_search.ui.models

sealed interface ScreenEvent {
    object CollapseSearchResults : ScreenEvent
    class Error(message: String) : ScreenEvent
}
