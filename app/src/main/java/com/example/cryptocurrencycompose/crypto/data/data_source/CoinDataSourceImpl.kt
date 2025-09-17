package com.example.cryptocurrencycompose.crypto.data.data_source

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cryptocurrencycompose.core.data.network.constructUrl
import com.example.cryptocurrencycompose.core.data.network.safeCall
import com.example.cryptocurrencycompose.core.domain.util.NetworkError
import com.example.cryptocurrencycompose.core.domain.util.Result
import com.example.cryptocurrencycompose.core.domain.util.map
import com.example.cryptocurrencycompose.crypto.data.model.CoinHistoryDto
import com.example.cryptocurrencycompose.crypto.data.model.CoinResponseDto
import com.example.cryptocurrencycompose.crypto.data.toCoin
import com.example.cryptocurrencycompose.crypto.data.toCoinPrice
import com.example.cryptocurrencycompose.crypto.domain.data_source.CoinDataSource
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import com.example.cryptocurrencycompose.crypto.domain.model.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinDataSourceImpl(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCoinsHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startTimeInMills = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        val endTimeInMills = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startTimeInMills)
                parameter("end", endTimeInMills)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}