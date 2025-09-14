package com.example.cryptocurrencycompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.example.cryptocurrencycompose.core.presentation.util.ObserveAsEvent
import com.example.cryptocurrencycompose.core.presentation.util.toString
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListEvent
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.ui.CoinListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyComposeTheme {
                val viewModel = koinViewModel<CoinListViewModel>()
                val coinState by viewModel.coinsState.collectAsStateWithLifecycle()

                ObserveAsEvent(event = viewModel.errorChannel) { event ->
                    when (event) {
                        is CoinListEvent.Error -> {
                            Toast.makeText(this, event.error.toString(this), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                CoinListScreen(state = coinState)
            }
        }
    }
}
