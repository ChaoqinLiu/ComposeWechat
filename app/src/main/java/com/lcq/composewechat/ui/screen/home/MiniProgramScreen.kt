package com.lcq.composewechat.ui.screen.home

import androidx.compose.animation.core.Spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.lcq.composewechat.R
import com.lcq.composewechat.data.MiniProgramItem
import com.lcq.composewechat.data.mineMiniProgramList
import com.lcq.composewechat.data.miniProgramList
import com.lcq.composewechat.extensions.click
import com.lcq.composewechat.extensions.overScrollVertical
import com.lcq.composewechat.extensions.parabolaScrollEasing

/**
 * author: liuchaoqin
 * 创建时间：2023/12/29
 * Describe ：
 */

@Composable
fun MiniProgramScreen(
    contentPadding: PaddingValues,
    popBackStack: ()-> Unit
) {
    val scrollState = rememberLazyListState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val springStiff by remember { mutableFloatStateOf(Spring.StiffnessLow) }
    val springDamp by remember { mutableFloatStateOf(Spring.DampingRatioLowBouncy) }
    val dragP by remember { mutableFloatStateOf(50f) }

    val target = with(LocalContext. current) {
        resources. displayMetrics. heightPixels / 4
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            contentPadding = contentPadding,
            state = scrollState,
            modifier = Modifier.fillMaxSize()
                .overScrollVertical(
                    isStartScroll = false,
                    isEndScroll = true,
                    nestedScrollToParent = false,
                    scrollEasing = { x1, x2 -> parabolaScrollEasing(x1, x2, dragP) },
                    springStiff = springStiff,
                    springDamp = springDamp,
                    scrollOffset = { offset ->
                        // 上拉超过target时返回首页
                        if (offset < -target) {
                            popBackStack()
                        }
                    }),
            ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "最近",
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 20.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        BasicTextField(
                            enabled = false,
                            value = textState.value,
                            onValueChange = {
                                textState.value = it
                            },
                            textStyle = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .width(70.dp)
                                .height(25.dp),
                            decorationBox = { innerTextField->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(Color(0xff434056))
                                        .padding(start = 6.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp),
                                            tint = Color(0xff8E8E9E)
                                        )
                                        Box(modifier = Modifier
                                            .wrapContentHeight()
                                            .wrapContentWidth()) {
                                            Text(
                                                "搜索",
                                                fontSize = 13.sp,
                                                color = Color(0xff8E8E9E),
                                                textAlign = TextAlign.Center,
                                            )
                                            innerTextField()
                                        }
                                    }
                                }
                            },
                        )
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(start = 30.dp, bottom = 15.dp, top = 30.dp)){
                    Text(
                        "音乐和视频",
                        fontSize = 14.sp,
                        color = Color(0xff8E8E9E),
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .height(90.dp)
                ) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(35.dp))
                                            .background(Color(0xff434056)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = rememberCoilPainter(request = R.mipmap.icon_music),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                    Text(
                                        "音乐",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()

                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(35.dp))
                                            .background(Color(0xff434056)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = rememberCoilPainter(request = R.mipmap.icon_audio),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                    Text(
                                        "音频",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                    ) {
                        Column() {
                            BasicTextField(
                                enabled = false,
                                value = textState.value,
                                onValueChange = {
                                    textState.value = it
                                },
                                textStyle = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(start = 10.dp)
                                ,
                                decorationBox = { innerTextField->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(40.dp))
                                            .background(Color(0xff434056))
                                            .padding(start = 6.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.PlayArrow,
                                                contentDescription = null,
                                                modifier = Modifier.size(30.dp),
                                                tint = Color(0xff8E8E9E)
                                            )
                                            Box(modifier = Modifier
                                                .wrapContentHeight()
                                                .wrapContentWidth()) {
                                                Text(
                                                    "暂无内容",
                                                    fontSize = 16.sp,
                                                    color = Color(0xff8E8E9E),
                                                    textAlign = TextAlign.Center,
                                                )
                                                innerTextField()
                                            }
                                        }
                                    }
                                },
                            )
                            Text(
                                "最近播放",
                                fontSize = 15.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            )
                        }
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, bottom = 15.dp, top = 30.dp, end = 30.dp)
                        .wrapContentHeight()
                ) {
                    Row {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Text(
                                "最近使用小程序",
                                fontSize = 14.sp,
                                color = Color(0xff8E8E9E),
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f).wrapContentHeight(align = Alignment.CenterVertically),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "更多",
                                    fontSize = 14.sp,
                                    color = Color(0xff8E8E9E),
                                )
                                Icon(
                                    Icons.Filled.ArrowForwardIos, null,
                                    modifier = Modifier.size(16.dp).padding(top = 4.dp),
                                    tint = Color(0xff8E8E9E),
                                )
                            }
                        }
                    }
                }
            }
            item {
                MiniProgramUI(miniProgramList)
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, bottom = 15.dp, top = 30.dp)
                        .wrapContentHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        "我的小程序",
                        fontSize = 14.sp,
                        color = Color(0xff8E8E9E),
                    )
                }
            }
            item {
                MiniProgramUI(mineMiniProgramList)
            }
            item { 
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(Color(0xff787493))
            .click {
                popBackStack()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "微信",
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            /* doSomething() */
                        }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        /* doSomething() */
                    }) {
                        Icon(
                            imageVector = Icons.Filled.AddCircleOutline,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniProgramUI(list: List<MiniProgramItem>) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        content = {
            items(list) {
                Box{
                    Column(
                        Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(35.dp))
                                .background(Color(0xff434056)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberCoilPainter(request = it.icon),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(35.dp))
                            )
                        }
                        Text(
                            it.title,
                            fontSize = 15.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    )
}