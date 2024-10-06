package com.lightfeather.userswithfavorite.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lightfeather.userswithfavorite.R


@Composable
fun ErrorPage(modifier: Modifier = Modifier) {
    Box(modifier) {
        Text(text = stringResource(R.string.something_went_wrong))
    }
}


@Preview
@Composable
private fun ErrorPagePreview() {

    ErrorPage()

}