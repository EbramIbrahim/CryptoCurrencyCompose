package com.example.cryptocurrencycompose.crypto.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cryptocurrencycompose.crypto.data.model.CoinDto
import com.example.cryptocurrencycompose.crypto.data.model.CoinPriceDto
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import com.example.cryptocurrencycompose.crypto.domain.model.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}
