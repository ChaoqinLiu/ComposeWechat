package com.lcq.composewechat.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lcq.composewechat.ui.route.HOME

/**
 * author: liuchaoqin
 * 创建时间：2023/12/8
 * Describe ：
 */

@Composable
fun HomeNavScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HOME //默认的启动页
    ) {
        composable(route = HOME) {
            HomeScreen(navController = navController)
        }
    }
}