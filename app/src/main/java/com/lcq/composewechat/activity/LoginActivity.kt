package com.lcq.composewechat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.enums.LifeState
import com.lcq.composewechat.ui.screen.login.LoginScreen
import com.lcq.composewechat.ui.screen.SplashScreen

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeLoginUI()
        }
    }
}

@Composable
fun ComposeLoginUI() {
    ProvideWindowInsets {
        val (appState, setAppState) = remember { mutableStateOf(LifeState.Splash) }

        when (appState) {
            LifeState.Splash -> {
                SplashScreen { setAppState(LifeState.Home) }
            }
            LifeState.Home -> {
                LoginScreen()
            }
        }
    }
}