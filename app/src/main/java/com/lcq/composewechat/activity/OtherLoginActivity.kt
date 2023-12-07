package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.ui.screen.login.LoginOtherScreen

class OtherLoginActivity : AppCompatActivity() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, OtherLoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeOtherLoginUI()
        }
    }
}

@Composable
private fun ComposeOtherLoginUI() {
    ProvideWindowInsets {
        LoginOtherScreen()
    }
}