package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.ui.screen.login.LoginPhoneScreen

class PhoneLoginActivity : AppCompatActivity() {
    companion object {
        fun navigate(context: Context, phone: String) {
            val intent = Intent(context, PhoneLoginActivity::class.java)
            intent.putExtra("phone", phone)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val phone: String? = intent.getStringExtra("phone")
        setContent {
            ComposePhoneLoginUI(phone ?: "")
        }
    }
}

@Composable
private fun ComposePhoneLoginUI(phone: String) {
    ProvideWindowInsets {
        LoginPhoneScreen(phone)
    }
}