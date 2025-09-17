package com.example.cryptocurrencycompose.crypto.domain.data_source

import com.example.cryptocurrencycompose.core.domain.util.NetworkError
import com.example.cryptocurrencycompose.core.domain.util.Result
import com.example.cryptocurrencycompose.crypto.data.model.CoinHistoryDto
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import com.example.cryptocurrencycompose.crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinsHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}