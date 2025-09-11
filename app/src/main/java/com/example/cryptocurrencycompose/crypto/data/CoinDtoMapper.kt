package com.example.cryptocurrencycompose.crypto.data

import com.example.cryptocurrencycompose.crypto.data.model.CoinDto
import com.example.cryptocurrencycompose.crypto.domain.model.Coin

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