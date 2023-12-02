package com.lcq.composewechat.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：
 */
data class ActionTitleItem(
    val title: String,
    val icon: Int,
    val image: String? = null,
)