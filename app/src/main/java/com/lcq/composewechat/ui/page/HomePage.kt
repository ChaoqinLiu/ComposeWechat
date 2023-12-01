package com.lcq.composewechat.ui.page

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.R
import com.lcq.composewechat.data.navList

/**
 * author: liuchaoqin
 * 创建时间：2023/11/30
 * Describe ：首页
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    var selectIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.height(60.dp)
                ) {
                    navList.forEachIndexed { index, nav ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp)
                                .clickable {
                                    selectIndex = index
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
            }
        ) {
            when(selectIndex){
                0 -> MessagePage()
                1 -> AddrBookPage()
                2 -> FindPage()
                3 -> MinePage()
            }
        }
    }
}