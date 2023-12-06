package com.lcq.composewechat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.compose.collectAsLazyPagingItems
import com.lcq.composewechat.enums.ChatAlign
import com.lcq.composewechat.enums.ChatType
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.viewmodel.paged.ChatMessageSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val rankChatItems: Flow<PagingData<ChatSession>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            ChatMessageSource()
        }.flow


    private val _messageFlow = MutableStateFlow<MutableList<ChatSession>>(mutableListOf())
    val mMessageFlow: StateFlow<List<ChatSession>> = _messageFlow

    fun sendMessage (message: String) {
        val session = ChatSession(
            "https://img.duoziwang.com/2018/24/12130927112411.jpg",
            "小美",
            message,
            ChatType.TEXT,
            ChatAlign.END
        )
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<ChatSession>()
            list.addAll(_messageFlow.value)
            list.add(session)
            list.reverse()
            _messageFlow.emit(list)
        }
    }
}