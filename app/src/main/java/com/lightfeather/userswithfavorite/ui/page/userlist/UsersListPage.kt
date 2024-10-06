package com.lightfeather.userswithfavorite.ui.page.userlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.lightfeather.userswithfavorite.data.DummyDataProvider
import com.lightfeather.userswithfavorite.ui.composable.ErrorPage
import com.lightfeather.userswithfavorite.ui.composable.LoadingPage
import com.lightfeather.userswithfavorite.ui.composable.UserItem
import com.lightfeather.userswithfavorite.ui.model.UiUser
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UsersListPage(
    source: UsersPageSource,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsersPageViewModel = koinViewModel(
        parameters = { parametersOf(source) }
    )
) {
    val state by viewModel.state.collectAsState()
    val intent by viewModel.intent.collectAsState(initial = null)
    LaunchedEffect(Unit) {
        viewModel.onIntent(UsersPageIntent.LoadUsers)
    }

    intent?.let { intentNotNull -> HandleIntent(intentNotNull, viewModel::onIntent, navController) }
    UsersListPageStateless(state, viewModel::onIntent, modifier)
}

@Composable
fun HandleIntent(
    intent: UsersPageIntent,
    onIntent: (UsersPageIntent) -> Unit,
    navController: NavController
) {
    when (intent) {
        is UsersPageIntent.NavigateToUser -> {
            navController.navigate(intent.user)
            onIntent(UsersPageIntent.Init)
        }

        else -> {}
    }

}

@Composable
fun UsersListPageStateless(state: UsersPageState, onIntent: (UsersPageIntent) -> Unit, modifier: Modifier = Modifier) {

    when {
        state.isLoading -> LoadingPage(modifier)
        state.error != null -> ErrorPage(modifier)
        else -> UsersList(state.users, onIntent, modifier)
    }

}

@Composable
private fun UsersList(users: List<UiUser>, onIntent: (UsersPageIntent) -> Unit, modifier: Modifier = Modifier) {

    LazyColumn(modifier.fillMaxSize()) {
        items(users) { user ->
            UserItem(
                user,
                onFavoriteClick = { onIntent(UsersPageIntent.ToggleFavorite(user)) }
            )
        }
    }

}

enum class UsersPageSource {
    ALL, FAVORITE
}


@Preview
@Composable
private fun UsersListPagePreview() {
    UsersListPageStateless(
        UsersPageState(
            users = List(10) { DummyDataProvider.dummyUiUser.copy(id = it.toString(), isFavorite = it % 3 == 0) }
        ),
        {},
        modifier = Modifier
    )

}