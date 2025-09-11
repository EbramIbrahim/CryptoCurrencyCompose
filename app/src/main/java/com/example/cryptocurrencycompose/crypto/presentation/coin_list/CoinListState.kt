package com.example.cryptocurrencycompose.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.example.cryptocurrencycompose.crypto.presentation.model.CoinUi


@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)
