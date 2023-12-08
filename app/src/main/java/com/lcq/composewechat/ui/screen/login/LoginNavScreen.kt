package com.lcq.composewechat.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lcq.composewechat.ui.route.LOGIN
import com.lcq.composewechat.ui.route.LOGIN_OTHER
import com.lcq.composewechat.ui.route.LOGIN_PHONE

/**
 * author: liuchaoqin
 * 创建时间：2023/12/8
 * Describe ：
 */

@Composable
fun LoginNavScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN //默认的启动页
    ) {
        composable(route = LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(route = LOGIN_OTHER) {
            LoginOtherScreen(navController = navController)
        }
        composable(
            route = "$LOGIN_PHONE/{phone}",
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })

        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            LoginPhoneScreen(navController = navController, phone = phone)
        }
    }
}