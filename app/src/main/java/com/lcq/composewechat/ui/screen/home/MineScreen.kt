package com.lcq.composewechat.ui.screen.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.R
import com.lcq.composewechat.data.ActionTitleItem
import com.lcq.composewechat.data.myAvatar
import com.lcq.composewechat.view.ActionTitle
import com.lcq.composewechat.view.OnActionClickListener

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@Composable
fun MineScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    val scrollState = rememberLazyListState()
    Surface {
        LazyColumn(
            contentPadding = innerPadding,
            state = scrollState,
            modifier = Modifier
                .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
                .fillMaxSize(),
        ) {
            item {
                HeaderItem(context)
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(
                    ActionTitleItem(
                    "服务",
                    R.mipmap.icon_service,
                ), context, false,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }

            item {
                ActionTitle(ActionTitleItem(
                    "收藏",
                    R.mipmap.icon_shoucang,
                ), context, true,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
            }
            item {
                ActionTitle(ActionTitleItem(
                    "朋友圈",
                    R.mipmap.icon_pengyouquan,
                ), context, true,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
            }
            item {
                ActionTitle(ActionTitleItem(
                    "卡包",
                    R.mipmap.icon_card,
                ), context, true,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
            }
            item {
                ActionTitle(ActionTitleItem(
                    "表情",
                    R.mipmap.icon_biaoqing,
                ), context, false,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
                CQDivider(thickness = 10.dp, colorId =  R.color.nav_bg)
            }
            item {
                ActionTitle(ActionTitleItem(
                    "设置",
                    R.mipmap.icon_set,
                ), context, false,
                    object : OnActionClickListener {
                        override fun onClick() {

                        }
                    })
            }

        }
    }
}

@Composable
fun HeaderItem(context: Context) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Image(
                painter = rememberCoilPainter(request = myAvatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = "李莫愁",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "微信号：17323345754",
                        fontSize = 16.sp,
                        color = Color(ContextCompat.getColor(context, R.color.gray)),
                        modifier = Modifier.weight(3f)
                    )
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 6.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Icon(
                            Icons.Filled.QrCode2, null,
                            modifier = Modifier.size(16.dp),
                            tint = Color(ContextCompat.getColor(context, R.color.gray)
                            )
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Icon(
                            Icons.Filled.ArrowForwardIos, null,
                            modifier = Modifier.size(14.dp),
                            tint = Color(ContextCompat.getColor(context, R.color.gray)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                    .width(50.dp)
                    .height(25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(width = 1.dp, color = Color(ContextCompat.getColor(context, R.color.gray_10)), shape = RoundedCornerShape(15.dp)),

                ) {
                    Row(modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Add, null,
                            modifier = Modifier.size(14.dp),
                            tint = Color(ContextCompat.getColor(context, R.color.gray)
                            )
                        )
                        Text(
                            text = "状态",
                            fontSize = 10.sp,
                            color = Color(ContextCompat.getColor(context, R.color.gray)),
                        )
                    }
                }

            }
        }
    }
}