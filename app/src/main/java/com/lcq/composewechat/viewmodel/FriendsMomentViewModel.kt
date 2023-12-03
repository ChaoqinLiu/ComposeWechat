package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lcq.composewechat.data.MomentItem
import com.lcq.composewechat.viewmodel.paged.FriendsMomentSource
import kotlinx.coroutines.flow.Flow

class FriendsMomentViewModel : ViewModel() {
    val rankMomentItems: Flow<PagingData<MomentItem>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            FriendsMomentSource()
        }.flow
}