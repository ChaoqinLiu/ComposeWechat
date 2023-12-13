package com.lcq.composewechat.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.lcq.composewechat.ui.screen.image.ImagePreviewScreen

/**
 * author: liuchaoqin
 * 创建时间：2023/12/3
 * Describe ：图片预览
 */
class ImagePreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val images: ArrayList<String> = intent.getStringArrayListExtra("images") as ArrayList<String>
        val currentIndex = intent.getIntExtra("currentIndex", 0)
        setContent {
            ImagePreviewScreen(images = images, currentIndex = currentIndex)
        }
    }

    companion object {
        fun navigate(context: Context, images: ArrayList<String>,  currentIndex: Int) {
            val intent = Intent(context, ImagePreviewActivity::class.java)
            intent.putStringArrayListExtra("images", images)
            intent.putExtra("currentIndex", currentIndex)
            context.startActivity(intent)
        }
    }
}