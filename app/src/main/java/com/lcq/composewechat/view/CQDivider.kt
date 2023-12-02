package com.lcq.composewechat

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：分割线
 */

@Composable
fun CQDivider(thickness: Dp? = 0.2.dp, colorId: Int? = R.color.gray_10) {
    val height = thickness ?: 0.2.dp
    val context = LocalContext.current
    val color = colorId ?: R.color.gray_10
    Divider(
        color = Color(ContextCompat.getColor(context, color)),
        thickness = height,
    )
}