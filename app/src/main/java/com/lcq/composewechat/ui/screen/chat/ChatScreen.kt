package com.lcq.composewechat.ui.screen.chat

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.R
import com.lcq.composewechat.data.myAvatar
import com.lcq.composewechat.enums.ChatAlign
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.ui.screen.Loading
import com.lcq.composewechat.viewmodel.ChatViewModel

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ChatViewModel = ChatViewModel()) {
    val context = LocalContext.current as Activity
    val scrollState = rememberLazyListState()
    var inputText by remember {mutableStateOf("")}
    val lazyChatItems = viewModel.rankChatItems.collectAsLazyPagingItems()
    rememberSystemUiController().setStatusBarColor(Color(ContextCompat.getColor(context, R.color.nav_bg)), darkIcons = true)
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "小美",
                            maxLines = 1,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(0xff000000)
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                /* doSomething() */
                            }) {
                            Icon(
                                imageVector = Icons.Filled.MoreHoriz,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xff000000)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(ContextCompat.getColor(context, R.color.nav_bg)),
                        scrolledContainerColor = Color(ContextCompat.getColor(context, R.color.nav_bg)),
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color(ContextCompat.getColor(context, R.color.black_10)),
                        actionIconContentColor = Color(ContextCompat.getColor(context, R.color.black_10)),
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                /* doSomething() */
                            }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        context.finish()
                                    },
                                tint = Color(0xff000000)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.height(60.dp),
                    containerColor = Color(ContextCompat.getColor(context, R.color.nav_bg)),
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        CQDivider()
                        Row(modifier = Modifier
                            .fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.BlurCircular,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color(0xff000000)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxHeight()
                                    .weight(6f)) {
                                OutlinedTextField(
                                    value = inputText,
                                    onValueChange = {
                                        inputText = it
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.White,
                                        focusedBorderColor = Color.White,
                                        unfocusedBorderColor = Color.White
                                    ),
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.TagFaces,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color(0xff000000)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircleOutline,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color(0xff000000)
                                )
                            }
                        }
                    }
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(ContextCompat.getColor(context, R.color.nav_bg))),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    LazyColumn(
                        state = scrollState,
                        contentPadding = innerPadding,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp,bottom = 60.dp),
                        reverseLayout = true,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        items(lazyChatItems) {
                            it?.let {
                                MessageItemView(it, context)
                            }
                        }

                        lazyChatItems.apply {
                            when (loadState.append) {
                                is LoadState.Loading -> {
                                    item { Loading() }
                                } else -> {}
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun MessageItemView(it: ChatSession, context: Context) {
    Box(
        contentAlignment = if (it.chatAlign == ChatAlign.START) Alignment.CenterStart else Alignment.CenterEnd,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(
                top = 30.dp,
                start = if (it.chatAlign == ChatAlign.START) 0.dp else 40.dp,
                end = if (it.chatAlign == ChatAlign.END) 0.dp else 40.dp
            ),
    ) {
        Row(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(Alignment.CenterVertically)
        ) {
            if(it.chatAlign == ChatAlign.START) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                        .weight(1f)
                ) {
                    Image(
                        painter = rememberCoilPainter(request = it.avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(6f)
                    .wrapContentHeight(Alignment.CenterVertically),
                contentAlignment = if (it.chatAlign == ChatAlign.START) Alignment.CenterStart else Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .wrapContentWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .background(if(it.chatAlign == ChatAlign.START) Color.White else Color(0xffB1E980))
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = it.message,
                        fontSize = 16.sp
                    )
                }
            }
            if(it.chatAlign == ChatAlign.END) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                        .weight(1f)
                ) {
                    Image(
                        painter = rememberCoilPainter(request = myAvatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }
        }
    }
}