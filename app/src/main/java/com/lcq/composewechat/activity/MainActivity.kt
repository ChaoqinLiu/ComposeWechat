package com.lcq.composewechat.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.enums.LifeState
import com.lcq.composewechat.ui.page.home.HomePage
import com.lcq.composewechat.ui.page.SplashPage
import com.lcq.composewechat.ui.theme.ComposeWechatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeHomeUI()
        }
    }
}

@Composable
fun ComposeHomeUI() {
    ComposeWechatTheme {
        ProvideWindowInsets {
            val (appState, setAppState) = remember { mutableStateOf(LifeState.Splash) }

            when (appState) {
                LifeState.Splash -> {
                    SplashPage { setAppState(LifeState.Home) }
                }
                LifeState.Home -> {
                    HomePage()
                }
            }
        }
    }
}