package alektas.term_search.ui.models

sealed interface ScreenEvent {
    object CollapseSearchResults : ScreenEvent
    class Error(val message: String) : ScreenEvent
}
