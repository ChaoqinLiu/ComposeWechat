package com.lcq.composewechat.ui.screen.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.R
import com.lcq.composewechat.data.AddrBookItem
import com.lcq.composewechat.data.headList
import com.lcq.composewechat.ui.book.StickyHeaderState
import com.lcq.composewechat.utils.book.AddrBookUtils
import com.lcq.composewechat.view.ActionBook
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/12/1
 * Describe ：
 */
@Composable
fun AddrBookScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val data = AddrBookUtils.getContactMap()
    val stickyHeaderState = rememberStickyHeaderState()
    stickyHeaderState.setData(data)
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    Box (
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(innerPadding)
    ){
        LazyColumLeftUI(data, context, stickyHeaderState)
        LazyColumRightUI(data, context, stickyHeaderState)
    }
}

@Composable
private fun LazyColumRightUI(data: Map<String, MutableList<AddrBookItem>>,context: Context, stickyState: StickyHeaderState) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.padding(end = 10.dp),
    ) {
        data.forEach { (initial, _) ->
            item {
                Text(
                    text = initial,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .size(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            scope.launch {
                                scope.launch {
                                    stickyState.scrollToItem(initial)
                                }
                            }
                        },
                    color = Color(ContextCompat.getColor(context, R.color.black_10)),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun rememberStickyHeaderState(
    state: LazyListState = LazyListState(),
    hashMap: HashMap<String, MutableList<AddrBookItem>> = HashMap()
): StickyHeaderState {
    return remember(state) {
        StickyHeaderState(
            state,
            hashMap
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumLeftUI(data: Map<String, MutableList<AddrBookItem>>, context: Context, stickyState: StickyHeaderState) {
    LazyColumn(state = stickyState.state) {
        item {
            for(i in headList.indices) {
                ActionBook(headList[i], context, i < headList.size -1)
            }
        }
        data.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                StickyHeaderTop(initial, context)
            }

            items(contactsForInitial.size) { contact ->
                StickyHeaderItem(contactsForInitial, contact, context)
            }
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun StickyHeaderTop(initial: String, context: Context) {
    CQDivider()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color(ContextCompat.getColor(context, R.color.nav_bg)))
            .padding(start = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            initial,
            color = Color(ContextCompat.getColor(context, R.color.black_10)),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun StickyHeaderItem(
    its: MutableList<AddrBookItem>,
    contact: Int,
    context: Context
) {
    Column {
        for(i in 0 until its.size) {
            ContactItem(its[i], context, its.size > 1)
        }
    }
}

@Composable
fun ContactItem(it: AddrBookItem, context: Context, showLine: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(45.dp)) {
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
                }
            }
        }
    }
    if (showLine) {
        Divider(
            color = Color(ContextCompat.getColor(context, R.color.gray_10)),
            thickness = 0.2.dp,
            modifier = Modifier.padding(start = 70.dp)
        )
    }
}
