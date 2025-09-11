package com.example.cryptocurrencycompose.crypto.data.data_source

import com.example.cryptocurrencycompose.core.data.network.constructUrl
import com.example.cryptocurrencycompose.core.data.network.safeCall
import com.example.cryptocurrencycompose.core.domain.util.NetworkError
import com.example.cryptocurrencycompose.core.domain.util.Result
import com.example.cryptocurrencycompose.core.domain.util.map
import com.example.cryptocurrencycompose.crypto.data.model.CoinResponseDto
import com.example.cryptocurrencycompose.crypto.data.toCoin
import com.example.cryptocurrencycompose.crypto.domain.data_source.CoinDataSource
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinDataSourceImpl(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}