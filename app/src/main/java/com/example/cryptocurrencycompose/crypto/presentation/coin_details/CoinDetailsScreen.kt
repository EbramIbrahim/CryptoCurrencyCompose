package com.example.cryptocurrencycompose.crypto.presentation.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencycompose.R
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.CoinListState
import com.example.cryptocurrencycompose.crypto.presentation.coin_list.ui.previewCoin
import com.example.cryptocurrencycompose.crypto.presentation.model.toDisplayedNumber
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme
import com.example.cryptocurrencycompose.ui.theme.greenBackground

@Composable
fun CoinDetailsScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {

    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        with(state.selectedCoin) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = icon
                    ),
                    contentDescription = name,
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    color = contentColor
                )
                Text(
                    text = symbol,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = contentColor
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    InfoCard(
                        title = stringResource(id = R.string.market_cap),
                        formattedText = "$ ${marketCapUsd.formattedValue}",
                        icon = ImageVector.vectorResource(R.drawable.stock)
                    )
                    InfoCard(
                        title = stringResource(id = R.string.price),
                        formattedText = "$ ${priceUsd.formattedValue}",
                        icon = ImageVector.vectorResource(R.drawable.dollar)
                    )
                    val absoluteChangeFormatted =
                        (priceUsd.value * (changePercent24Hr.value / 100))
                            .toDisplayedNumber()
                    val isPositive = changePercent24Hr.value > 0.0
                    val contentColor = if(isPositive) {
                        if(isSystemInDarkTheme()) Color.Green else greenBackground
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                    InfoCard(
                        title = stringResource(id = R.string.change_last_24h),
                        formattedText = absoluteChangeFormatted.formattedValue,
                        icon = if(isPositive) {
                            ImageVector.vectorResource(id = R.drawable.trending)
                        } else {
                            ImageVector.vectorResource(id = R.drawable.trending_down)
                        },
                        contentColor = contentColor
                    )

                }
            }
        }
    }
}


@Preview
@PreviewLightDark
@Composable
private fun CoinDetailsPrev() {
    CryptoCurrencyComposeTheme {
        CoinDetailsScreen(
            state = CoinListState(
                selectedCoin = previewCoin,
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

