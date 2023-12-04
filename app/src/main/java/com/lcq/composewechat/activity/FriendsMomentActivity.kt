package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lcq.composewechat.ui.screen.montent.FriendsMomentScreen
import com.lcq.composewechat.ui.theme.ComposeWechatTheme

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：朋友圈
 */
class FriendsMomentActivity : AppCompatActivity() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, FriendsMomentActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MomentPage()
        }
    }
}

@Composable
fun MomentPage() {
    ProvideWindowInsets {
        FriendsMomentScreen()
    }
}