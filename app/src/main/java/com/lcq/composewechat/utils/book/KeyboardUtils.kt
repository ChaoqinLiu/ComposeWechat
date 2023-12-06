package com.lcq.composewechat.utils.book

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * author: liuchaoqin
 * 创建时间：2023/12/6
 * Describe ：键盘工具类
 */

/**
 * 隐藏软键盘
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autoCloseKeyboard(): Modifier = composed {
    //LocalSoftwareKeyboardController 这个是compose 组件，必须在compose 函数内才能使用
    val keyboardController = LocalSoftwareKeyboardController.current
    pointerInput(this) {
        detectTapGestures(
            onPress = {
                keyboardController?.hide()
            }
        )
    }
}