package com.example.cryptocurrencycompose.navigator

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptocurrencycompose.core.presentation.util.ObserveAsEvent
import com.example.cryptocurrencycompose.core.presentation.util.toString
import com.example.cryptocurrencycompose.crypto.presentation.coin_details.CoinDetailsScreen
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListAction
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListEvent
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.ui.CoinListScreen
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CoinAdaptiveNavigator(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {

    val coinState by viewModel.coinsState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvent(event = viewModel.errorChannel) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(context, event.error.toString(context), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = coinState,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when (action) {
                            is CoinListAction.OnCoinClicked -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailsScreen(
                    state = coinState,
                )
            }
        }
    )
}





