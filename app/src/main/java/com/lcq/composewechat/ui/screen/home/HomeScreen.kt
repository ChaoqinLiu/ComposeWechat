package com.lcq.composewechat.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.R
import com.lcq.composewechat.data.navList
import com.lcq.composewechat.data.titles
import com.lcq.composewechat.extensions.click
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/11/30
 * Describe ：首页
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    var selectIndex by rememberSaveable { mutableStateOf(0) }
    val pageState = rememberPagerState(initialPage = 0)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
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
                            titles[selectIndex],
                            maxLines = 1,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        if(selectIndex != 3) {
                            IconButton(
                                onClick = {
                                    /* doSomething() */
                                }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color(ContextCompat.getColor(context, R.color.black_10))
                                )
                            }
                            IconButton(onClick = {
                                /* doSomething() */
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircleOutline,
                                    contentDescription = null,
                                    modifier = Modifier.size(25.dp),
                                    tint = Color(ContextCompat.getColor(context, R.color.black_10))
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(ContextCompat.getColor(context, if (selectIndex != 3) R.color.nav_bg else R.color.white)),
                        scrolledContainerColor = Color(ContextCompat.getColor(context, if (selectIndex != 3) R.color.nav_bg else R.color.white)),
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color(ContextCompat.getColor(context, R.color.black_10)),
                        actionIconContentColor = Color(ContextCompat.getColor(context, R.color.black_10)),
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.height(60.dp),
                    containerColor = Color(ContextCompat.getColor(context, if (selectIndex > 1) R.color.white else R.color.nav_bg)),
                ) {
                    navList.forEachIndexed { index, nav ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .click {
                                    selectIndex = index
                                    /**
                                     * 点击底部的tab切换对应的page
                                     */
                                    scope.launch {
                                        pageState.scrollToPage(index)
                                    }
                                }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Icon(
                                    nav.icon, null,
                                    modifier = Modifier.size(28.dp),
                                    tint = Color(
                                        if (selectIndex == index) ContextCompat.getColor(context, R.color.green)
                                        else ContextCompat.getColor(context, R.color.gray)
                                    )
                                )
                                Text(
                                    text = nav.title,
                                    fontSize = 12.sp,
                                    color = Color(
                                        if (selectIndex == index) ContextCompat.getColor(context, R.color.green)
                                        else ContextCompat.getColor(context, R.color.gray)
                                    )
                                )
                            }
                        }
                    }
                }
            },
            content = { innerPadding ->
                Box {
                    HorizontalPager(
                        count = 4,
                        state = pageState,
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        when(page) {
                            0 -> ChatSessionScreen(innerPadding)
                            1 -> AddrBookScreen(innerPadding)
                            2 -> FindScreen(innerPadding)
                            3 -> MineScreen(innerPadding)
                        }
                    }
                    LaunchedEffect(pageState) {
                        snapshotFlow { pageState.currentPage }.collect { page ->
                            selectIndex = page
                            println("LaunchedEffect currentPage: $page")
                        }
                    }
                }
            }
        )
    }
}
