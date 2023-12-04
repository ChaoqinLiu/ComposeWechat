package com.lcq.composewechat.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.R
import com.lcq.composewechat.data.MessageItem
import com.lcq.composewechat.data.messageList
import com.lcq.composewechat.CQDivider

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatSessionScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    val scrollState = rememberLazyListState()
    Surface {
        LazyColumn(
            contentPadding = innerPadding,
            //verticalArrangement = Arrangement.spacedBy(8.dp),
            state = scrollState
        ) {
            item {
                CQDivider()
            }
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(40.dp, 0.dp, 35.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PersonalVideo,
                            contentDescription = null,
                            modifier = Modifier.size(23.dp),
                            tint = Color(ContextCompat.getColor(context, R.color.gray))
                        )
                        Text(
                            text = "mac 微信已登陆",
                            color = Color(ContextCompat.getColor(context, R.color.gray)),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(25.dp, 0.dp, 0.dp,0.dp)
                        )
                    }
                }
            }
            item {
                CQDivider()
            }
            items(messageList) {
                it.let {
                    Column {
                        MessageItem(it = it, context)
                        Divider(
                            color = Color(ContextCompat.getColor(context, R.color.gray_10)),
                            thickness = 0.2.dp,
                            modifier = Modifier.padding(70.dp, 0.dp,0.dp,0.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun MessageItem(it: MessageItem, context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(50.dp)) {
                Image(
                    painter = rememberCoilPainter(request = it.avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
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
                        text = it.name,
                        fontSize = 17.sp,
                        color = Color(ContextCompat.getColor(context, R.color.black)),
                    )
                    Text(
                        text = it.message,
                        fontSize = 12.sp,
                        color = Color(ContextCompat.getColor(context, R.color.gray_10)),
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)) {
                    Text(
                        text = it.lastTime,
                        fontSize = 12.sp,
                        color = Color(ContextCompat.getColor(context, R.color.gray_10)),
                    )
                }
            }
        }
    }
}