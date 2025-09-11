package com.example.cryptocurrencycompose.core.presentation.util

import com.example.cryptocurrencycompose.R

// TODO() add all coins to this list
fun coinCodeToResource(code: String): Int {
    return when(code.uppercase()) {
        "INCH" -> R.drawable._inch
        else -> R.drawable._inch
    }
}