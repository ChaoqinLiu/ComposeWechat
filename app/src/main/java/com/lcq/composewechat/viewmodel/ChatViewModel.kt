package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.viewmodel.paged.ChatMessageSource
import kotlinx.coroutines.flow.Flow

class ChatViewModel : ViewModel() {
    val rankChatItems: Flow<PagingData<ChatSession>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            ChatMessageSource()
        }.flow
}