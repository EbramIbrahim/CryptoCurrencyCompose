package com.example.cryptocurrencycompose.core.presentation.util

import com.example.cryptocurrencycompose.R

// TODO() add all coins to this list
fun coinCodeToResource(code: String): Int {
    return when(code.uppercase()) {
        "BTC" -> R.drawable.btc
        "INCH" -> R.drawable._inch
        "ST" -> R.drawable._st
        "XBTC" -> R.drawable._xbtc
        "AAVE" -> R.drawable.aave
        else -> R.drawable._inch
    }
}