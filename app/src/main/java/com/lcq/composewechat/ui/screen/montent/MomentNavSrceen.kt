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
 * Describe ：
 */

@Composable
fun MomentNavScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FRIENDS_MOMENT //默认的启动页
    ) {
        composable(route = FRIENDS_MOMENT) {
            MomentScreen(navController = navController)
        }
        composable(
            route = IMAGE_BROWSER,
        ) {
            val images = it.arguments?.getStringArrayList("images") ?: ArrayList()
            val currentIndex = it.arguments?.getInt("currentIndex") ?: 0
            ImageBrowserScreen(images = images, currentIndex = currentIndex,navController = navController)
        }
    }
}