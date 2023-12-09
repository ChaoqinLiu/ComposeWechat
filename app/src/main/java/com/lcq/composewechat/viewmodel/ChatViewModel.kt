package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lcq.composewechat.enums.MessageType
import com.lcq.composewechat.enums.MediaType
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.viewmodel.paged.ChatMessageSource
import github.leavesczy.compose_chat.base.utils.TimeUtils.currentTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val rankChatItems: Flow<PagingData<ChatSession>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            ChatMessageSource()
        }.flow


    private val _messageFlow = MutableStateFlow<MutableList<ChatSession>>(mutableListOf())
    val mMessageFlow: StateFlow<List<ChatSession>> = _messageFlow

    fun sendMessage (message: String, messageType: MessageType) {
        val session = ChatSession(
            "https://img.duoziwang.com/2018/24/12130927112411.jpg",
            "小美",
            if (messageType == MessageType.SEND) message else "收到：$message",
            MediaType.TEXT,
            messageType,
            currentTimeMillis()
        )
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<ChatSession>()
            list.add(session)
            list.addAll(_messageFlow.value)
            _messageFlow.emit(list)
        }
    }
}