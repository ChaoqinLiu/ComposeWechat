package com.lcq.composewechat.ui.screen.chat

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
import com.lcq.composewechat.entitys.ChatSmsEntity
import com.lcq.composewechat.enums.MessageType
import com.lcq.composewechat.models.ChatSession
import com.lcq.composewechat.ui.screen.Loading
import com.lcq.composewechat.extensions.autoCloseKeyboard
import com.lcq.composewechat.extensions.click
import com.lcq.composewechat.ui.screen.EmojiPicker
import com.lcq.composewechat.viewmodel.ChatViewModel
import github.leavesczy.compose_chat.base.utils.TimeUtils.toTalkTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun ChatScreen(viewModel: ChatViewModel, session: ChatSession) {
    val context = LocalContext.current as Activity
    val scrollState = rememberLazyListState()
    /** 输入框使用TextFieldValue替换inputText，解决表情包赋值时光标没有在最后的问题*/
    //var inputText by remember { mutableStateOf("") }
    val textState = remember { mutableStateOf(TextFieldValue()) }
    /** 聊天消息 */
    val lazyChatItems = viewModel.chatSmsItems.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    rememberSystemUiController().setStatusBarColor(Color(ContextCompat.getColor(context, R.color.nav_bg)), darkIcons = true)
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .autoCloseKeyboard()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            session.name,
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
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PauseCircleOutline,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color(0xff000000)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .fillMaxHeight()
                                    .weight(6f)
                            ) {
                                BasicTextField(
                                    value = textState.value,
                                    onValueChange = {
                                        textState.value = it
                                    },
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .defaultMinSize(minHeight = 45.dp, minWidth = 280.dp)
                                        .focusRequester(focusRequester),
                                    decorationBox = { innerTextField->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color.White)
                                                .padding(start = 6.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            innerTextField()
                                        }
                                    },
                                    /** 光标颜色*/
                                    cursorBrush = SolidColor(Color(0xff5ECC71))
                                )
                            }
                            if (textState.value.text.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (!sheetState.isVisible )Icons.Filled.TagFaces else Icons.Filled.BlurCircular,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .click {
                                                focusRequester.requestFocus()
                                                scope.launch {
                                                    if (!sheetState.isVisible) {
                                                        keyboardController?.hide()
                                                        sheetState.show()
                                                    } else {
                                                        sheetState.hide()
                                                        keyboardController?.show()
                                                    }
                                                }
                                            },
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
                            } else {
                                Box(modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        top = 12.dp,
                                        bottom = 12.dp,
                                        end = 10.dp
                                    )
                                    .fillMaxHeight()
                                    .weight(2f),
                                    contentAlignment = Alignment.Center ) {
                                    Text(
                                        text = "发送",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xff5ECC71))
                                            .padding(top = 4.dp)
                                            .clickable {
                                                viewModel.sendMessage(textState.value.text, MessageType.SEND)
                                                scope.launch {
                                                    /** 发送信息后滚动到最底部*/
                                                    scrollState.scrollToItem(0)
                                                    /** 清空输入框的值*/
                                                    textState.value = TextFieldValue("")
                                                    sheetState.hide()
                                                    /** 本人发送一条信息后保存一条对面回复模拟信息*/
                                                    delay(200)
                                                    viewModel.sendMessage(
                                                        "",
                                                        MessageType.RECEIVE
                                                    )
                                                }
                                            },
                                        textAlign = TextAlign.Center
                                    )
                                }
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
                                MessageItemView(it, session)
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
                    EmojiPicker(
                        modalBottomSheetState = sheetState,
                        onPicked = { emoji ->
                            val text = textState.value.text + emoji
                            /** 输入表情包时将光标置于最后，不然一直在最前面*/
                            textState.value = TextFieldValue(text, selection = TextRange(text.length -1))
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun MessageItemView(it: ChatSmsEntity, session: ChatSession) {
    Box(
        contentAlignment = if (it.messageType == MessageType.RECEIVE.value) Alignment.CenterStart else Alignment.CenterEnd,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 30.dp,
                start = if (it.messageType == MessageType.RECEIVE.value) 0.dp else 40.dp,
                end = if (it.messageType == MessageType.SEND.value) 0.dp else 40.dp
            ),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            /*** 对话时间*/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
               Text(
                   text = toTalkTime(it.createBy),
                   fontSize = 12.sp,
                   color = Color(0xff888888)
               )
            }
            /*** 对话信息*/
            Row(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
            ) {
                /*** 他人头像（左边）*/
                if(it.messageType == MessageType.RECEIVE.value) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White)
                            .weight(1f)
                    ) {
                        Image(
                            painter = rememberCoilPainter(request = session.avatar),
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
                        .wrapContentHeight(),
                    contentAlignment =
                    if (it.messageType == MessageType.RECEIVE.value) Alignment.TopStart
                    else Alignment.TopEnd
                ) {
                    /*** 尖角*/
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        contentAlignment = if (it.messageType == MessageType.RECEIVE.value) Alignment.TopStart else Alignment.TopEnd
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(if (it.messageType == MessageType.SEND.value) 0f else 180f),
                            tint = if (it.messageType == MessageType.RECEIVE.value) Color.White
                            else Color(0xffA9EA7A)
                        )
                    }
                    /*** 文本内容*/
                    Box(
                        modifier = Modifier
                            .padding(
                                start = if (it.messageType == MessageType.RECEIVE.value) 12.dp else 0.dp,
                                end = if (it.messageType == MessageType.RECEIVE.value) 0.dp else 12.dp,
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .wrapContentWidth()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .background(
                                if (it.messageType == MessageType.RECEIVE.value) Color.White else Color(
                                    0xffA9EA7A
                                )
                            ),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it.message,
                            fontSize = 16.sp
                        )
                    }
                }
                /*** 本人头像（右边）*/
                if(it.messageType == MessageType.SEND.value) {
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
}