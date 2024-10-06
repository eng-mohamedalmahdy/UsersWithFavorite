package com.lightfeather.userswithfavorite.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lightfeather.userswithfavorite.data.DummyDataProvider
import com.lightfeather.userswithfavorite.ui.model.UiUser


@Composable
fun UserItem(
    user: UiUser, modifier: Modifier = Modifier,
    onFavoriteClick: (UiUser) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(text = user.name, style = MaterialTheme.typography.titleLarge)
                Text(text = user.username, style = MaterialTheme.typography.bodyMedium)
                Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
                Text(text = user.phone, style = MaterialTheme.typography.bodyMedium)
                Text(text = user.website, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (user.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (user.isFavorite) "Favorite" else "Not Favorite",
                modifier = Modifier.size(24.dp).clickable { onFavoriteClick(user) }
            )
        }
    }
}

@Preview
@Composable
private fun UserItemPreview() {
    Column {
        UserItem(DummyDataProvider.dummyUiUser, modifier = Modifier.padding(8.dp)){}
        UserItem(DummyDataProvider.dummyUiUser.copy(isFavorite = true), modifier = Modifier.padding(8.dp)){}
    }

}