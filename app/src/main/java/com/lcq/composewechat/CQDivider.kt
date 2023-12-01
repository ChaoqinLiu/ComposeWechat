package com.lcq.composewechat

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：分割线
 */

@Composable
fun CQDivider() {
    val context = LocalContext.current
    Divider(
        color = Color(ContextCompat.getColor(context, R.color.gray_10)),
        thickness = 0.2.dp,
    )
}