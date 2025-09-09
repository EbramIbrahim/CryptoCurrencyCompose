package com.example.cryptocurrencycompose.presentation.model

import androidx.annotation.DrawableRes
import com.example.cryptocurrencycompose.core.util.coinCodeToResource
import com.example.cryptocurrencycompose.domain.model.Coin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: String,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayedNumber,
    val priceUsd: DisplayedNumber,
    val changePercent24Hr: DisplayedNumber,
    @DrawableRes val icon: Int
)

data class DisplayedNumber(
    val value: Double,
    val formattedValue: String,
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayedNumber(),
        priceUsd = priceUsd.toDisplayedNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayedNumber(),
        icon = coinCodeToResource(symbol)
    )
}

fun Double.toDisplayedNumber(): DisplayedNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayedNumber(
        value = this,
        formattedValue = formatter.format(this)
    )
}




