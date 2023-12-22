package com.lcq.composewechat.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.lcq.composewechat.R
import com.lcq.composewechat.data.MessageItem
import com.lcq.composewechat.data.messageList
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.activity.ChatActivity
import com.lcq.composewechat.utils.DensityUtils.pxToDp
import com.lcq.composewechat.viewmodel.ChatSessionViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatSessionScreen(innerPadding: PaddingValues, viewModel: ChatSessionViewModel = ChatSessionViewModel()) {
    val context = LocalContext.current
    /*** 获取状态栏高度 */
    val statusBarHeight = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    var offsetY by remember { mutableStateOf(0f) }
    val max = with(LocalContext. current) {
        resources. displayMetrics. heightPixels / 3
    }
    val offset by viewModel.mOffsetY.collectAsState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                offsetY = (offsetY + available.y).coerceIn(0f, max.toFloat())
                scope.launch {
                    viewModel.setOffsetY(offsetY)
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(top = statusBarHeight)
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = innerPadding,
            state = scrollState,
            modifier = Modifier.nestedScroll(connection = nestedScrollConnection)
        ) {
//            item {
//                PullHeaderMask(scrollState, offset, max)
//            }
            stickyHeader {
                TopAppBar(context)
            }
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

/**
 * 标题栏
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "微信(8)",
                maxLines = 1,
                fontSize = 16.sp,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row() {
                IconButton(
                    onClick = {
                        /* doSomething() */
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.black_10
                            )
                        )
                    )
                }
                IconButton(onClick = {
                    /* doSomething() */
                }) {
                    Icon(
                        imageVector = Icons.Filled.AddCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = Color(
                            ContextCompat.getColor(
                                context,
                                R.color.black_10
                            )
                        )
                    )
                }
            }
        }
    }
}

/**
 * 下拉三点动画部分
 */
@Composable
private fun PullHeaderMask(scrollState: LazyListState, offsetY: Float, max: Int) {
    /*** 滚动的目标值 */
    val target = LocalDensity.current.run { max.toFloat() }
    /*** 列表的索引值 */
    val firstVisibleItemIndex = remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    /*** 当前滚动的值（Y轴距离）*/
    val firstVisibleItemScrollOffset = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    /*** 滚动的百分比 */
    val scrollPercent: Float = if (firstVisibleItemIndex.value > 0) {
        0f
    } else {
        1- (firstVisibleItemScrollOffset.value / target)
    }

    //println("scrollPercent=======$scrollPercent")
    val backgroundColor = Color(0xffEDEDED)
    val density = LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(pxToDp(offsetY.toInt(), density))
            //.alpha(scrollPercent)
            .background(backgroundColor),
            //.animateContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "12334444")
    }
}

@Composable
fun MessageItem(it: MessageItem, context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(70.dp)
            .clickable {
                ChatActivity.navigate(context, it)
            }
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