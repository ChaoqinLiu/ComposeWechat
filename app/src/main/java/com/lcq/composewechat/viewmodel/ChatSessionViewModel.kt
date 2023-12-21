package com.lcq.composewechat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcq.composewechat.models.ChatSession
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/12/15
 * Describe ：
 */
class ChatSessionViewModel: ViewModel() {

    private val _offsetY = MutableStateFlow<Float>(0f)
    val mOffsetY: StateFlow<Float> = _offsetY

    suspend fun setOffsetY(offsetY: Float) {
        viewModelScope.launch {
            delay(20)
            _offsetY.emit(offsetY)
        }
    }
}