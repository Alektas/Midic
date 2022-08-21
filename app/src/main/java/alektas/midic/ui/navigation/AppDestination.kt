package alektas.midic.ui.navigation

sealed class AppDestination(val route: String) {

    object TermSearch : AppDestination("term_search")

    object BookmarkList : AppDestination("bookmark_list")

}
