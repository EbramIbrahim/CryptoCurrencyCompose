package com.example.cryptocurrencycompose.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
) {

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.coinList) { coin ->
            CoinListItem(
                coinUi = coin,
                onClick = {},
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.background
                )
            )
        }
    }

}

@Preview
@PreviewLightDark
@Composable
private fun CoinListScreenPreview () {
    CryptoCurrencyComposeTheme {
        CoinListScreen(
            state = CoinListState(
                isLoading = false,
                coinList = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            )
        )
    }
}










