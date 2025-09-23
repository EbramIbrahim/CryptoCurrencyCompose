package com.example.cryptocurrencycompose.crypto.presentation.coin_list

import com.example.cryptocurrencycompose.crypto.presentation.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClicked(val coinUi: CoinUi): CoinListAction
}