package com.lcq.composewechat

import android.app.Application

/**
 * author: liuchaoqin
 * 创建时间：2023/12/9
 * Describe ：
 */
class App : Application() {

    companion object {
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
