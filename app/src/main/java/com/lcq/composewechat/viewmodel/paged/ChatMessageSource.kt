package com.lcq.composewechat.viewmodel.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcq.composewechat.data.chatList
import com.lcq.composewechat.models.ChatSession
import kotlinx.coroutines.delay

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：
 */
class ChatMessageSource: PagingSource<Int, ChatSession>() {

    override fun getRefreshKey(state: PagingState<Int, ChatSession>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatSession> {
        return try {
            val nextPage = params.key ?: 1
            val chatListResponse = chatList
            if (nextPage > 1) {
                delay(2000)
            }
            LoadResult.Page(
                data = chatListResponse,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}