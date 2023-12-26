package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.lcq.composewechat.data.MessageItem
import com.lcq.composewechat.enums.MessageType
import com.lcq.composewechat.enums.MediaType
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.ui.screen.chat.ChatScreen
import com.lcq.composewechat.viewmodel.ChatViewModel
import github.leavesczy.compose_chat.base.utils.TimeUtils

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
                ChatScreen(viewModel = ChatViewModel(userId = session.userId), session = session)
            }
        }
    }

    companion object {
        fun navigate(context: Context,messageItem: MessageItem) {
            val intent = Intent(context, ChatActivity::class.java)
            val session = ChatSession(
                messageItem.userId,
                messageItem.avatar,
                messageItem.name,
                messageItem.message,
                MediaType.TEXT,
                MessageType.RECEIVE,
                TimeUtils.currentTimeMillis()
            )
            intent.putExtra("chatSession", session)
            context.startActivity(intent)
        }
    }
}