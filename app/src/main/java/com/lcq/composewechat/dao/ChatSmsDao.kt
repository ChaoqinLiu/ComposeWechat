package com.lcq.composewechat.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lcq.composewechat.entitys.ChatSmsEntity

/**
 * author: liuchaoqin
 * 创建时间：2023/12/26
 * Describe ：
 */
@Dao
interface ChatSmsDao {
    /**
     * 新增聊天记录
     */
    @Insert
    suspend fun insert(entity: ChatSmsEntity): Long

    /**
     * 删除指定聊天记录
     */
    @Query("DELETE FROM chatsms WHERE id=:id")
    suspend fun delete(id: Long)

    /**
     * 删除指定用户聊天记录
     */
    @Query("DELETE FROM chatsms WHERE userId =:userId")
    suspend fun deleteAllByUserId(userId: Long)

    /**
     * 删除所有聊天记录
     */
    @Query("DELETE FROM chatsms")
    suspend fun deleteAll()

    /**
     * 分页查询聊天记录
     */
    @Query("SELECT * FROM chatsms WHERE userId =:userId ORDER BY createBy DESC")
    fun getAllByPagingSource(userId: Long): PagingSource<Int, ChatSmsEntity>
}