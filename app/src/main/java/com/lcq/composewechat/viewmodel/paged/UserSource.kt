package com.lcq.composewechat.viewmodel.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcq.composewechat.api.UserApi
import com.lcq.composewechat.http.HttpClient
import com.lcq.composewechat.models.User

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */
class UserSource: PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPage = params.key ?: 1
        val data = HttpClient.create(UserApi::class.java).getUserList(nextPage, 10)
        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
           LoadResult.Error(e)
        }
    }
}