package com.example.cryptocurrencycompose.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencycompose.crypto.presentation.model.DisplayedNumber
import com.example.cryptocurrencycompose.ui.theme.greenBackground

@Composable
fun ChangePercentItem(
    changePercent: DisplayedNumber
) {

    val contentColor = if (changePercent.value < 0.0) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }

    val backgroundColor = if (changePercent.value < 0.0) {
        MaterialTheme.colorScheme.onError
    } else {
        greenBackground
    }


    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (changePercent.value > 0.0)
                Icons.Default.KeyboardArrowUp
            else
                Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "${changePercent.value} %",
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )


    }

}


@Preview
@Composable
private fun ChangeItemPreview() {
    ChangePercentItem(
        changePercent = DisplayedNumber(
            value = 1.7,
            formattedValue = "1.7 %"
        )
    )
}



