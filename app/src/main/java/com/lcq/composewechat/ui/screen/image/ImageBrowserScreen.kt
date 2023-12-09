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
import kotlin.math.absoluteValue

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：图片预览
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageBrowserScreen(images: ArrayList<String>, currentIndex: Int) {

    /**
     * 界面状态变更
     */
    val pageState = rememberPagerState(initialPage = currentIndex)

    Box {
        HorizontalPager(
            count = images.size,
            state = pageState,
            contentPadding = PaddingValues(horizontal = 0.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            ImageBrowserItem(images[page], page, this)
        }

        HorizontalPagerIndicator(
            pagerState = pageState,
            activeColor = Color.White,
            inactiveColor = Color(0xff888888),
            indicatorHeight = 6.dp,
            indicatorWidth = 6.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(60.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageBrowserItem(image: String, page: Int = 0, pagerScope: PagerScope) {
    /**
     * 缩放比例
     */
    var scale by remember { mutableStateOf(1f) }

    val context = LocalContext.current as Activity

    /**
     * 偏移量
     */
    var offset  by remember { mutableStateOf(Offset.Zero) }

    /**
     * 监听手势状态变换
     */
    val state =
        rememberTransformableState(onTransformation = { zoomChange, panChange, rotationChange ->
            scale = (zoomChange * scale).coerceAtLeast(1f)
            scale = if (scale > 5f) {
                5f
            } else {
                scale
            }
        })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black,
    ) {
        Image(
            painter = rememberCoilPainter(
                request = image
            ),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .transformable(state = state)
                .graphicsLayer{  //布局缩放、旋转、移动变换
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y

                    val pageOffset = pagerScope.calculateCurrentOffsetForPage(page = page).absoluteValue
                    if (pageOffset == 1.0f) {
                        scale = 1.0f
                    }
                }
                .pointerInput(Unit) {
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
                }
        )
    }
}
