package com.example.cryptocurrencycompose.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


@Composable
fun <T> ObserveAsEvent(
    event: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent:(T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner, key1, key2) {
        event.collect(onEvent)
    }
}














