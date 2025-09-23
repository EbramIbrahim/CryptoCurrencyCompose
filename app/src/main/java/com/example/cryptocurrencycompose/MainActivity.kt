package com.example.cryptocurrencycompose

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.cryptocurrencycompose.core.presentation.util.ObserveAsEvent
import com.example.cryptocurrencycompose.core.presentation.util.toString
import com.example.cryptocurrencycompose.crypto.presentation.coin_details.CoinDetailsScreen
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListEvent
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.ui.CoinListScreen
import com.example.cryptocurrencycompose.navigator.CoinAdaptiveNavigator


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoinAdaptiveNavigator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
