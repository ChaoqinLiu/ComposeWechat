package com.lcq.composewechat.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */

@Composable
fun Loading() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
        .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFFCCCCCC),
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                strokeWidth = 1.5.dp
            )
            Text(
                text = "正在加载...",
                fontSize = 13.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}