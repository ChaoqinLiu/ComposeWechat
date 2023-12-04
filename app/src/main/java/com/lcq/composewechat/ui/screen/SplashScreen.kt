package com.lcq.composewechat.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import com.lcq.composewechat.R

/**
 * author: liuchaoqin
 * 创建时间：2023/11/30
 * Describe ：启动页
 */

@Composable
fun SplashScreen(onSplashCompleted: () -> Unit) {
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Box( modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
            LaunchedEffect(Unit) {
                delay(1000)
                onSplashCompleted()
            }
            Image(
                painter = painterResource(id = R.drawable.bg_splash),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}