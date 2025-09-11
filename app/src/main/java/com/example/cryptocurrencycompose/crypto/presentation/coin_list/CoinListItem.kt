package com.example.cryptocurrencycompose.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencycompose.crypto.domain.model.Coin
import com.example.cryptocurrencycompose.crypto.presentation.model.CoinUi
import com.example.cryptocurrencycompose.crypto.presentation.model.toCoinUi
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
) {

    val contentColor = if (isSystemInDarkTheme())
        Color.White
    else
        Color.Black

    Row(
        modifier = modifier
            .clickable { onClick }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Icon(
            imageVector = ImageVector.vectorResource(coinUi.icon),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
        ) {

            Text(
                text = coinUi.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )

            Text(
                text = coinUi.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${coinUi.priceUsd.formattedValue}",
                fontSize = 14.sp,
                color = contentColor,
                fontWeight = FontWeight.SemiBold
            )
            ChangePercentItem(
                changePercent = coinUi.changePercent24Hr
            )

        }

    }
}

@Preview
@PreviewLightDark
@Composable
private fun CoinItemPreview() {
    CryptoCurrencyComposeTheme {
    CoinListItem(
        coinUi = previewCoin,
        onClick = {},
        modifier = Modifier.background(
            MaterialTheme.colorScheme.background
        )
    )
    }
}

internal val previewCoin = Coin(
    id = "1",
    rank = "2",
    name = "BITCOIN",
    symbol = "BTC",
    marketCapUsd = 1214155555999.3,
    priceUsd = 222431.99,
    changePercent24Hr = 1.4
).toCoinUi()












