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
import com.lcq.composewechat.ui.screen.login.LoginNavScreen
import com.lcq.composewechat.utils.EasyDataStore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeLoginUI()
        }
        /**
         * 记录密码后直接进入主页
         */
        val password = EasyDataStore.getData("password", "")
        if (password != "") {
            GlobalScope.launch {
                delay(500)
                MainActivity.navigate(this@LoginActivity)
            }
        }
    }
}

@Composable
private fun ComposeLoginUI() {
    ProvideWindowInsets {
        val (appState, setAppState) = remember { mutableStateOf(LifeState.Splash) }

        when (appState) {
            LifeState.Splash -> {
                SplashScreen { setAppState(LifeState.Home) }
            }
            LifeState.Home -> {
                LoginNavScreen()
            }
        }
    }
}