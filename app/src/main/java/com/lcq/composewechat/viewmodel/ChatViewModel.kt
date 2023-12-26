package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lcq.composewechat.db.COMChatDataBase
import com.lcq.composewechat.entitys.ChatSmsEntity
import com.lcq.composewechat.enums.MessageType
import com.lcq.composewechat.enums.MediaType
import github.leavesczy.compose_chat.base.utils.TimeUtils.currentTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ChatViewModel(userId: Long) : ViewModel() {

    private val _userId: Long = userId

    val chatSmsItems: Flow<PagingData<ChatSmsEntity>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            /** 查询表里的聊天记录 */
            COMChatDataBase.instance.chatSmsDao().getAllByPagingSource(_userId)
        }.flow
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)

    /**
     * 模拟对方发来的随机对话
     */
    private val _mockReceiveSms = listOf(
        "你好啊，最近过得怎么样？",
        "嗨，我挺好的，谢谢关心。最近工作有点忙，但也在努力调整自己的状态。",
        "是啊，工作总是让人有些疲惫。你有什么放松的方法吗？",
        "你对未来有什么规划吗？",
        "其实我在考虑出国留学，但还在考虑中。你呢？有什么打算？",
        "我打算创业，自己开一家咖啡店。希望能在未来几年内实现这个梦想",
        "你觉得什么样的人最吸引你？",
        "是啊，和有趣的人在一起总是让人感到快乐。我也很喜欢和有趣的人交朋友",
        "我觉得不诚实、虚伪、不尊重他人的人最不吸引我。我觉得人与人之间的尊重和理解很重要",
        "您能告诉我您的时间安排吗？"
    )

    /**
     * 发送信息（保存到本地数据库）
     */
    fun sendMessage (message: String, messageType: MessageType) {
        val entity: ChatSmsEntity
        if (messageType == MessageType.SEND) {
            entity = ChatSmsEntity(
                userId = _userId,
                mediaType = MediaType.TEXT.value,
                message = message,
                messageType = messageType.value,
                createBy = currentTimeMillis()
            )
        } else {
            val random = Random()
            /** 生成0-9之间的随机数*/
            val index = random.nextInt(10)
            entity = ChatSmsEntity(
                userId = _userId,
                mediaType = MediaType.TEXT.value,
                message = _mockReceiveSms[index],
                messageType = MessageType.RECEIVE.value,
                createBy = currentTimeMillis()
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val id = COMChatDataBase.instance.chatSmsDao().insert(entity)
            println("id============$id")
        }
    }
}