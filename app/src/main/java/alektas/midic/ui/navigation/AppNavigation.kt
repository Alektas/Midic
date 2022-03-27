package alektas.midic.ui.navigation

import alektas.midic.di.AppComponent
import alektas.midic.ui.features.BookmarkListFeature
import alektas.midic.ui.features.TermSearchFeature
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(appComponent: AppComponent) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppDestination.TermSearch.route) {
        composable(AppDestination.TermSearch.route) {
            TermSearchFeature(
                appComponent,
                onBookmarksClick = { navController.navigate(AppDestination.BookmarkList.route) }
            )
        }
        composable(AppDestination.BookmarkList.route) {
            BookmarkListFeature(
                appComponent,
                onBackButtonClick = { navController.navigateUp() }
            )
        }
    }
}
