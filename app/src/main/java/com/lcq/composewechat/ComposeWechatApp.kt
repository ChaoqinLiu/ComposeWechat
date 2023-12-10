package com.lcq.composewechat

import android.app.Application

/**
 * author: liuchaoqin
 * 创建时间：2023/12/9
 * Describe ：
 */
class ComposeWechatApp : Application() {

    companion object {
        lateinit var instance : ComposeWechatApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
