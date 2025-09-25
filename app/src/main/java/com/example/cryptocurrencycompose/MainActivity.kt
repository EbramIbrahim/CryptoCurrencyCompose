package com.example.cryptocurrencycompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.cryptocurrencycompose.ui.theme.CryptoCurrencyComposeTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.cryptocurrencycompose.navigator.CoinAdaptiveNavigator


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //NavigationSuitLayout()
                    CoinAdaptiveNavigator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/// Adaptive navigation bar with material 3

@Composable
fun NavigationSuitLayout(modifier: Modifier = Modifier) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            Screens.entries.forEachIndexed { index, screen ->
                item(
                    selected = index == selectedIndex,
                    onClick = { selectedIndex = index },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title
                        )
                    },
                    label = {
                        Text(text = screen.title)
                    },
                )
            }
        },
        layoutType = if(windowWidthClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                currentWindowAdaptiveInfo()
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Screens.entries.forEachIndexed { index, screen ->
                if(selectedIndex == index) {
                    Text(text = screen.title)
                }
            }
        }
    }
}


enum class Screens(val title: String, val icon: ImageVector) {
    Home("Home", Icons.Filled.Home),
    Profile("Profile", Icons.Filled.AccountCircle),
    Details("Details", Icons.Filled.Info)
}







