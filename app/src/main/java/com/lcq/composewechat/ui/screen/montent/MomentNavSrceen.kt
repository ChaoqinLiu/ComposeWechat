package com.lcq.composewechat.ui.screen.montent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lcq.composewechat.ui.route.FRIENDS_MOMENT
import com.lcq.composewechat.ui.route.IMAGE_BROWSER
import com.lcq.composewechat.ui.screen.image.ImageBrowserScreen
import com.lcq.composewechat.ui.screen.montent.MomentScreen

/**
 * author: liuchaoqin
 * 创建时间：2023/12/8
 * Describe ：本来想使用navController处理朋友圈和图片预览的，
 * 但是预览回来后朋友圈列表出现抖动，所以还是使用Activity方式
 */

@Composable
fun MomentNavScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FRIENDS_MOMENT //默认的启动页
    ) {
        composable(route = FRIENDS_MOMENT) {
            MomentScreen()
        }
        composable(
            route = IMAGE_BROWSER,
        ) {
            val images = it.arguments?.getStringArrayList("images") ?: ArrayList()
            val currentIndex = it.arguments?.getInt("currentIndex") ?: 0
            ImageBrowserScreen(images = images, currentIndex = currentIndex)
        }
    }
}