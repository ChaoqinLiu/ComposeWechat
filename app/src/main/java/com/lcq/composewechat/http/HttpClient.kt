package com.lcq.composewechat.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */
object HttpClient {

    private const val BASE_URL = "http://192.168.15.30:8080"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz) as T
    }
}