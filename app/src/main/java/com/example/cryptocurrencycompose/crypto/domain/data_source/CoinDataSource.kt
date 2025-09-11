package com.example.cryptocurrencycompose.crypto.domain.data_source

import com.example.cryptocurrencycompose.core.domain.util.NetworkError
import com.example.cryptocurrencycompose.core.domain.util.Result
import com.example.cryptocurrencycompose.crypto.domain.model.Coin

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}