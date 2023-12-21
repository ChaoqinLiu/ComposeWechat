package com.lcq.composewechat.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.data.ActionTitleItem
import com.lcq.composewechat.view.ActionTitle
import com.lcq.composewechat.R
import com.lcq.composewechat.activity.MomentActivity

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@Composable
fun FindScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    Surface {
        LazyColumn(
            contentPadding = innerPadding,
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
        ) {
            item {
                CQDivider()
            }
            item {
                ActionTitle(ActionTitleItem(
                    "朋友圈",
                    R.mipmap.icon_friends,
                    "https://img.duoziwang.com/2019/07/12080849900677.jpg"
                ), context, false,
                    onClick = {
                        MomentActivity.navigate(context)
                    })
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "直播",
                    R.mipmap.icon_live,
                ), context, false,
                    onClick = {})
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "扫一扫",
                    R.mipmap.icon_scan,
                ), context, true,
                    onClick = {})
            }
            item {
                ActionTitle(ActionTitleItem(
                    "摇一摇",
                    R.mipmap.icon_yao,
                ), context, true,
                    onClick = {})
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "看一看",
                    R.mipmap.icon_look,
                ), context, true,
                    onClick = {})
            }
            item {
                ActionTitle(ActionTitleItem(
                    "搜一搜",
                    R.mipmap.icon_search,
                ), context, true,
                    onClick = {})
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "附近",
                    R.mipmap.icon_near,
                ), context, false,
                    onClick = {})
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "小程序",
                    R.mipmap.icon_applet,
                ), context, false,
                    onClick = {})
            }
        }
    }
}