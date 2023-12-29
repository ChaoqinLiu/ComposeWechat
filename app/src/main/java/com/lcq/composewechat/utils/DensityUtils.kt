package com.lcq.composewechat.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * author: liuchaoqin
 * 创建时间：2023/12/15
 * Describe ：
 */
object DensityUtils {

    fun px2dp(px: Int, density: Float): Dp {
       return  (px / density).dp
    }
}