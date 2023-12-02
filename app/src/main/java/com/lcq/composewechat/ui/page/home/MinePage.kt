package com.lcq.composewechat.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@Composable
fun MinePage(innerPadding: PaddingValues) {
    val context = LocalContext.current
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    val scrollState = rememberLazyListState()
    Surface {
        LazyColumn(
            contentPadding = innerPadding,
            state = scrollState
        ) {
            item {
                CQDivider()
            }
        }
    }
}