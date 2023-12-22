package com.lcq.composewechat.ui.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.extensions.autoCloseKeyboard
import com.lcq.composewechat.ui.screen.Loading
import com.lcq.composewechat.ui.screen.montent.MomentItemView
import com.lcq.composewechat.viewmodel.UserViewModel

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(viewModel: UserViewModel = UserViewModel()) {
    val lazyUserItems = viewModel.userItems.collectAsLazyPagingItems()
    val scrollState = rememberLazyListState()
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .autoCloseKeyboard()
    ) {
        Scaffold(
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    LazyColumn(
                        contentPadding = innerPadding,
                        state = scrollState
                    ) {
                        items(lazyUserItems) {
                            it?.let {
                                Column {
                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "name: ${it.name},  userId: ${it.userId}",
                                            fontSize = 18.sp
                                        )
                                    }
                                    CQDivider()
                                }
                            }
                        }
                        lazyUserItems.apply {
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
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        )
    }
}