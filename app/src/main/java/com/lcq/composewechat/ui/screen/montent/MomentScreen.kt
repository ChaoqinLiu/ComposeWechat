package com.lcq.composewechat.ui.screen.montent

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.activity.ImageBrowserActivity
import com.lcq.composewechat.data.MomentItem
import com.lcq.composewechat.data.myAvatar
import com.lcq.composewechat.ui.screen.Loading
import com.lcq.composewechat.viewmodel.MomentViewModel

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：朋友圈
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MomentScreen(
    viewModel: MomentViewModel = MomentViewModel(),
) {
    /**
     * 状态栏设置
     */
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false,
        )
    }
    /**
     * 获取状态栏高度
     */
    val statusBarHeight = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val lazyMomentItems = viewModel.rankMomentItems.collectAsLazyPagingItems()
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    Box {
        LazyColumn(
            contentPadding = PaddingValues(0.dp),
            state = scrollState,
        ) {
            item {
                MomentTopItem()
            }
            items(lazyMomentItems) {
                it?.let {
                    MomentItemView(it, context)
                }
            }

            lazyMomentItems.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Loading()
                        }
                    } else -> {
                }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        MomentHeader(scrollState, statusBarHeight, systemUiController)
    }
}

@Composable
fun MomentScreenUI() {}

@Composable
fun MomentItemView(it: MomentItem, context: Context) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Row() {
            Box(modifier = Modifier
                .padding(top = 4.dp)
                .width(45.dp)
                .height(45.dp)
                ) {
                Image(
                    painter = rememberCoilPainter(request = it.avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )
            }
            MomentImageItemView(it, context)
        }
    }
}

@Composable
fun MomentImageItemView(it: MomentItem, context: Context) {
    val defaultHeight = 100.dp
    var height: Dp = defaultHeight
    val size = it.images.size
    if (size > 6) {
        height =  defaultHeight * 3
    } else if(size in 4..6) {
        height =  defaultHeight * 2
    }
    Column {
        Text(
            text = it.name,
            modifier = Modifier.padding(start = 6.dp),
            fontSize = 16.sp,
            color = Color(0xff61698e)

        )
        Text(
            text = it.content,
            modifier = Modifier.padding(start = 6.dp),
            fontSize = 16.sp,
            color = Color(0xff1e1e1e)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(start = 10.dp, top = 0.dp)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                content = {
                    itemsIndexed(it.images) { index, photo ->
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val images: ArrayList<String> = ArrayList()
                                    it.images.forEachIndexed { _, s ->
                                        images.add(s)
                                    }
                                    ImageBrowserActivity.navigate(
                                        context = context,
                                        images = images,
                                        currentIndex = index
                                    )
                                }
                        ) {
                            Image(
                                painter = rememberCoilPainter(request = photo),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = it.createTime,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f),
                fontSize = 12.sp,
                color = Color(0xff1e1e1e),
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp, bottom = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFEDEDED)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xff000000)
                    )
                }
            }
        }
        if (it.comment != null && it.comment != "") {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFEDEDED))
            ) {
                Text(
                    text = it.comment,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    fontSize = 14.sp,
                    color = Color(0xff1e1e1e),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

/**
 * 顶部的朋友圈背景图
 */
@Composable
fun MomentTopItem() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)) {
        /**
         * 背景图片
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F01%2F20200701134954_yVnHK.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1704867043&t=210af5b7a7cb8def124dac87711ebf47"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                //Spacer(modifier = Modifier.statusBarsHeight())
            }
        }
        /**
         * 右下角的个人信息
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .padding(top = 260.dp, end = 15.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row() {
                Text(
                    text = "李莫愁",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 6.dp, top = 13.dp)
                )
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
            }
        }
    }
}

/**
 * 朋友圈标题栏
 */
@Composable
fun MomentHeader(
    scrollState: LazyListState,
    statusBarHeight: Dp,
    systemUiController: SystemUiController
) {
    val context = LocalContext.current as Activity
    /**
     *  滚动距离的目标值
     */
    val target = LocalDensity.current.run { 250.dp.toPx() }
    /**
     * 列表的索引值
     */
    val firstVisibleItemIndex = remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    /**
     * 当前滚动的值（Y轴距离）
     */
    val firstVisibleItemScrollOffset = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    /**
     * 滚动的百分比
     */
    val scrollPercent: Float = if (firstVisibleItemIndex.value > 0) {
        1f
    } else {
        firstVisibleItemScrollOffset.value / target
    }

    /**
     * 定义一个变量记录状态栏的颜色设置，避免滚动时一直在修改
     */
    var isTransparent by rememberSaveable { mutableStateOf(true) }
    if (scrollPercent > 0) {
        if (isTransparent) {
            systemUiController.setSystemBarsColor(
                color = Color(0xFFEDEDED),
                darkIcons = true,
            )
            isTransparent = false
        }
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false,
        )
        isTransparent = true
    }
    val backgroundColor = Color(0xFFEDEDED)
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = statusBarHeight)
                .statusBarsHeight()
                .alpha(scrollPercent)
                .background(backgroundColor)
        )
        Box(modifier = Modifier.height(44.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(scrollPercent)
                    .background(backgroundColor)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterStart)
                            .clickable {
                                context.finish()
                            },
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "朋友圈",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(scrollPercent)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 15.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterEnd),
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
            }
        }
    }
}