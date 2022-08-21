package alektas.ui_components.app_bar

import alektas.ui_components.R
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    backBtnCallback: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    SmallTopAppBar(
        modifier = modifier,
        title = { title?.let { AppBarTitle(title) } },
        navigationIcon = { backBtnCallback?.let { NavigationIcon(it) } },
        actions = actions,
    )
}

@Composable
private fun AppBarTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
fun NavigationIcon(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.descr_back_btn)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarWithTitleLight() {
    TopAppBar(title = "Settings")
}

@Preview(showBackground = true)
@Composable
fun TopAppBarWithBackAndTitleLight() {
    TopAppBar(title = "Settings", backBtnCallback = {})
}

@Preview(showBackground = true)
@Composable
fun TopAppBarWithActionsLight() {
    TopAppBar(
        actions = {
            NavigationIcon { }
            NavigationIcon { }
            NavigationIcon { }
        }
    )
}
