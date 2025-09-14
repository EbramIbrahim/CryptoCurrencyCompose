package com.example.cryptocurrencycompose.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencycompose.core.domain.util.onError
import com.example.cryptocurrencycompose.core.domain.util.onSuccess
import com.example.cryptocurrencycompose.crypto.domain.data_source.CoinDataSource
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import com.example.cryptocurrencycompose.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _coinsState: MutableStateFlow<CoinListState> = MutableStateFlow(CoinListState())
    val coinsState = _coinsState
        .onStart { loadCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = CoinListState()
        )

    private val _errorChannel: Channel<CoinListEvent> = Channel()
    val errorChannel = _errorChannel.receiveAsFlow()


    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClicked -> TODO()
            CoinListAction.RefreshCoins -> {
                loadCoins()
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _coinsState.update { it.copy(isLoading = true) }
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _coinsState.update {
                        it.copy(
                            isLoading = false,
                            coinList = coins.map { it.toCoinUi() })
                    }
                }
                .onError { error ->
                    _coinsState.update { it.copy(isLoading = false) }
                    _errorChannel.send(CoinListEvent.Error(error))
                }
        }
    }

}
