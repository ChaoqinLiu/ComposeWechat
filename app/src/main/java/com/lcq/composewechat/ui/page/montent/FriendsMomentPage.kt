package com.lcq.composewechat.ui.page.montent

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.paging.LoadState
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.R
import com.lcq.composewechat.data.MomentItem
import com.lcq.composewechat.data.momentList
import com.lcq.composewechat.ui.page.*

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：朋友圈
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsMomentPage() {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    Box {
        LazyColumn(state = scrollState) {
            item {
                MomentTopItem()
            }
            items(momentList) {
                MomentItemView(it)
            }

//            lazyMovieItems.apply {
//                when (loadState.append) {
//                    is LoadState.Loading -> {
//                        item { LoadingItem() }
//                    }
//                    else -> {
//                    }
//                }
//            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        MomentHeader(scrollState)
    }
}

@Composable
fun MomentItemView(it: MomentItem) {
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
            MomentImageItemView(it)
        }
    }
}

@Composable
fun MomentImageItemView(it: MomentItem) {
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
                .padding(start = 10.dp)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                content = {
                    items(it.images) { photo ->
                        Image(
                            painter = rememberCoilPainter(request = photo),
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(),
                            contentDescription = null,
                        )
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
                    .padding(end = 4.dp, top = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xff000000)
                )
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

@Composable
fun MomentTopItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = rememberCoilPainter(request = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202005%2F17%2F20200517115233_ZMzvN.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1704099655&t=1de9e0c8fa0062c3a405316a7bd66009"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.statusBarsHeight())
        }
    }
}

@Composable
fun MomentHeader(scrollState: LazyListState) {
    val target = LocalDensity.current.run {
        200.dp.toPx()
    }
    val scrollPercent: Float = if (scrollState.firstVisibleItemIndex > 0) {
        1f
    } else {
        scrollState.firstVisibleItemScrollOffset / target
    }
    Log.d("4444444444444", "scrollPercent==$scrollPercent")
    val activity = LocalContext.current as Activity
    val backgroundColor = Color(0xFFEDEDED)
    Column() {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
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
                    modifier = Modifier.weight(1f).padding(start = 15.dp).fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                            .align(Alignment.CenterStart),
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
                Box(
                    modifier = Modifier.weight(4f).fillMaxHeight(),
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
                    modifier = Modifier.weight(1f).padding(end = 15.dp).fillMaxHeight(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                            .align(Alignment.CenterEnd),
                        tint = if (scrollPercent > 0) Color(0xff2E2E2E) else Color.White
                    )
                }
            }

        }
    }
}