package com.lcq.composewechat.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.lcq.composewechat.R
import com.lcq.composewechat.data.MessageItem
import com.lcq.composewechat.data.messageList
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.activity.ChatActivity
import com.lcq.composewechat.extensions.*
import com.lcq.composewechat.utils.DensityUtils.px2dp
import com.lcq.composewechat.viewmodel.ChatSessionViewModel
import kotlin.math.roundToInt

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatSessionScreen(innerPadding: PaddingValues, viewModel: ChatSessionViewModel = ChatSessionViewModel(), onChangeVisible: (visible: Boolean) -> Unit) {
    val context = LocalContext.current
    /*** 获取状态栏高度 */
    val statusBarHeight = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val scrollState = rememberLazyListState()

    var offset by remember { mutableStateOf(0f) }

    val fullHeight = with(LocalContext. current) {
        resources. displayMetrics. heightPixels
    }
    val density = LocalDensity.current.density

    val springStiff by remember { mutableFloatStateOf(Spring.StiffnessLow) }
    val springDamp by remember { mutableFloatStateOf(Spring.DampingRatioLowBouncy) }
    val dragP by remember { mutableFloatStateOf(50f) }

    var visible by remember { mutableStateOf(false) }

    /*** 滚动的百分比 */
    var scrollPercent by remember { mutableFloatStateOf(0f) }
    /** 中间圆的大小*/
    var ballSize by remember { mutableFloatStateOf(0f) }
    val target = fullHeight / 4
    val backgroundColor = Color(0xffEDEDED)

    var offsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .padding(top = statusBarHeight)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .alpha(scrollPercent)
            .background(Color(0xff1B1B2B)),
        ) {
            MiniProgramScreen(
                contentPadding = innerPadding,
                popBackStack = {
                    visible = false
                    scrollPercent = 0f
                }
            )
        }
        /** 遮罩层*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(if (scrollPercent < 0.9) backgroundColor else Color.Transparent),
        )
        /** 三个点的动画*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .height(px2dp((offset.toInt()), density))
                .alpha(1 - scrollPercent)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .size(if (scrollPercent > 0.15f) (if (scrollPercent > 0.3f) 6.dp else 10.dp) else ballSize.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xff303030))
            )
            Box(modifier = Modifier
                .offset { IntOffset(if (scrollPercent > 0.15f) -offsetX.roundToInt() else 0, 0) }
                .size(6.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xff303030))
            )
            Box(modifier = Modifier
                .offset { IntOffset(if (scrollPercent > 0.15f) offsetX.roundToInt() else 0, 0) }
                .size(6.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xff303030))
            )
        }

        AnimatedVisibility(
            visible = !visible,
            enter = slideInVertically(initialOffsetY = {fullHeight}),
            exit = slideOutVertically(targetOffsetY = {fullHeight})
        ) {
            LazyColumn(
                contentPadding = innerPadding,
                state = scrollState,
                modifier = Modifier
                    .overScrollVertical(
                        isStartScroll = true,
                        isEndScroll = false,
                        nestedScrollToParent = false,
                        scrollEasing = { x1, x2 -> parabolaScrollEasing(x1, x2, dragP) },
                        springStiff = springStiff,
                        springDamp = springDamp,
                        scrollOffset = { x3 ->
                            offset = x3
                            if (offset > target) {
                                visible = true
                                scrollPercent = 1.0f
                            } else if (!visible) {
                                scrollPercent = offset / target
                            }
                            scrollPercent = if (scrollPercent < 0f) 0.0f else scrollPercent
                            ballSize = scrollPercent * 70
                            println("===offset:$offset ====visible:$visible ====scrollPercent:$scrollPercent")
                            onChangeVisible(visible)
                            offsetX = scrollPercent * 100
                        })
                    .alpha(1 - scrollPercent)
                    .background(Color.White),
                flingBehavior = rememberOverscrollFlingBehavior { scrollState }
            ) {
                stickyHeader {
                    TopAppBar(context)
                }
                item {
                    CQDivider()
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(Color(0xFFEDEDED))
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
                                modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
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
                                modifier = Modifier.padding(70.dp, 0.dp, 0.dp, 0.dp)
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