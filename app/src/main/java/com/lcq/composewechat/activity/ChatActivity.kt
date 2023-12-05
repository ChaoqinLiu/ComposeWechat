package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.lcq.composewechat.ui.screen.chat.ChatScreen

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：聊天界面
 */

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatScreen()
        }
    }

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, ChatActivity::class.java)
            context.startActivity(intent)
        }
    }
}