package com.lcq.composewechat.ui.screen.image

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.extensions.detectCustomTransformGestures
import kotlin.math.absoluteValue

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：图片预览
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePreviewScreen(images: ArrayList<String>, currentIndex: Int) {

    rememberSystemUiController().setStatusBarColor(Color.Black, darkIcons = true)
    /*** 界面状态变更 */
    val pageState = rememberPagerState(initialPage = currentIndex)

    Box {
        /*** 图片部分 */
        HorizontalPager(
            count = images.size,
            state = pageState,
            contentPadding = PaddingValues(horizontal = 0.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            ImagePageItem(images[page], page, this)
        }
        /*** 指示器部分 */
        HorizontalPagerIndicator(
            pagerState = pageState,
            activeColor = Color.White,              /*** 指示器选中的颜色 */
            inactiveColor = Color(0xff888888), /*** 指示器未选中的颜色 */
            indicatorHeight = 6.dp,                  /*** 指示器的高度 */
            indicatorWidth = 6.dp,                   /*** 指示器的宽度 */
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(60.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePageItem(
    image: String,
    page: Int = 0,
    pagerScope: PagerScope
) {
    val context = LocalContext.current as Activity
    /*** 缩放比例 */
    var scale by remember { mutableStateOf(1f) }
    /*** 偏移量 */
    var offset  by remember { mutableStateOf(Offset.Zero) }
    /*** 监听手势变化 */
    val state = rememberTransformableState(onTransformation = { zoomChange, offsetChange, rotationChange ->
        scale = (zoomChange * scale).coerceAtLeast(1f)
        scale = if (scale > 4f) {
            4f
        } else {
            scale
        }
        offset += offsetChange
    })
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black,
    ) {
        Image(
            painter = rememberCoilPainter(
                request = image
            ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                //.transformable(state = state)         /** 检测手势元素的平移、缩放、旋转 */
                .graphicsLayer{  /**缩放、旋转、移动变换 */
                    scaleX = scale                    /** 等比缩放 */
                    scaleY = scale                    /** 等比缩放 */
                    translationX = offset.x           /** X轴位移量*/
                    translationY = offset.y           /** Y轴位移量*/

                    /** 页面切换时需要恢复scale */
                    val pageOffset = pagerScope.calculateCurrentOffsetForPage(page = page).absoluteValue
                    if (pageOffset == 1.0f) {
                        scale = 1.0f
                    }
                }.pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            scale = if (scale <= 1f) {
                                2f
                            } else {
                                1f
                            }
                            offset = Offset.Zero
                        },
                        onTap = {
                            context.finish()
                        }
                    )
                }.pointerInput(Unit) {
                    detectCustomTransformGestures(
                        consume = false,
                        onGesture = { _, pan, gestureZoom, _, _, changes ->
                            scale = (gestureZoom * scale).coerceAtLeast(1f)
                            scale = if (scale > 4f) {
                                4f
                            } else {
                                scale
                            }
                            offset += pan
                            // If more than 1 pointer is down consume event
                            // to prevent Pager from scrolling
                            if (changes.size > 1) {
                                changes.forEach { it.consume() }
                            }
                        }
                    )
                }
        )
    }
}
