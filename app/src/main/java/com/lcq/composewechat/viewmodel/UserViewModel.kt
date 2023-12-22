package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lcq.composewechat.models.User
import com.lcq.composewechat.viewmodel.paged.UserSource
import kotlinx.coroutines.flow.Flow

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */
class UserViewModel: ViewModel() {

    val userItems: Flow<PagingData<User>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            UserSource()
        }.flow.cachedIn(viewModelScope)
}