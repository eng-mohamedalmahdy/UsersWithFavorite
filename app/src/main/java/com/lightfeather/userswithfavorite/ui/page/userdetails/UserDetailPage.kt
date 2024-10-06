package com.lightfeather.userswithfavorite.ui.page.userdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lightfeather.userswithfavorite.ui.model.UiUser
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersPageIntent
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersPageSource
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersPageViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun UserDetailPage(
    user: UiUser, vm: UsersPageViewModel = koinViewModel(
        parameters = {
            parametersOf(UsersPageSource.ALL)
        }
    )
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Row {
            Text(text = user.name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = if (user.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (user.isFavorite) "Favorite" else "Not Favorite",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        vm.onIntent(UsersPageIntent.ToggleFavorite(user))
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Information
        Text(text = "@${user.username}", style = MaterialTheme.typography.bodyMedium)
        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        Text(text = user.phone, style = MaterialTheme.typography.bodyMedium)
        Text(text = user.website, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(16.dp))


    }
}
