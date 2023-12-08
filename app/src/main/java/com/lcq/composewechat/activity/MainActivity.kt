package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.ui.screen.home.HomeNavScreen
import com.lcq.composewechat.ui.screen.home.HomeScreen

class MainActivity : ComponentActivity() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
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
    ProvideWindowInsets {
        HomeNavScreen()
    }
}