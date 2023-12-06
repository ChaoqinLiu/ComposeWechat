package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.lcq.composewechat.data.MessageItem
import com.lcq.composewechat.enums.ChatAlign
import com.lcq.composewechat.enums.ChatType
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.ui.screen.chat.ChatScreen

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：聊天界面
 */

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val session = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { intent.getParcelableExtra("chatSession", ChatSession::class.java) } else { intent.getParcelableExtra("chatSession") }
        setContent {
            if (session != null) {
                ChatScreen(session = session)
            }
        }
    }

    companion object {
        fun navigate(context: Context,messageItem: MessageItem) {
            val intent = Intent(context, ChatActivity::class.java)
            val session = ChatSession(
                messageItem.avatar,
                messageItem.name,
                messageItem.message,
                ChatType.TEXT,
                ChatAlign.START
            )
            intent.putExtra("chatSession", session)
            context.startActivity(intent)
        }
    }
}