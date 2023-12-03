package com.lcq.composewechat.viewmodel.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcq.composewechat.data.MomentItem
import com.lcq.composewechat.data.momentList
import kotlinx.coroutines.delay

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：
 */
class FriendsMomentSource: PagingSource<Int, MomentItem>() {

    override fun getRefreshKey(state: PagingState<Int, MomentItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MomentItem> {
        return try {
            val nextPage = params.key ?: 1
            val momentListResponse = momentList
            if (nextPage > 1) {
                delay(2000)
            }
            LoadResult.Page(
                data = momentListResponse,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}