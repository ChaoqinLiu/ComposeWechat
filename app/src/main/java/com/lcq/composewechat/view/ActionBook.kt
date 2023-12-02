package com.lcq.composewechat.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.lcq.composewechat.R
import com.lcq.composewechat.data.HeaderItem

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：
 */

@Composable
fun ActionBook(it: HeaderItem, context: Context, showLine: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(it.background),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    it.icon, null,
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .weight(3f)
                ) {
                    Text(
                        text = it.title,
                        fontSize = 17.sp,
                        color = Color(ContextCompat.getColor(context, R.color.black)),
                    )
                }
            }
        }
    }
    if (showLine) {
        Divider(
            color = Color(ContextCompat.getColor(context, R.color.gray_10)),
            thickness = 0.2.dp,
            modifier = Modifier.padding(start = 70.dp)
        )
    }
}