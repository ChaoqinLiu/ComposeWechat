package com.lcq.composewechat.api

import com.lcq.composewechat.models.User
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */

interface UserApi{

    @GET("/users/getUserList")
    suspend fun getUserList(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): List<User>
}