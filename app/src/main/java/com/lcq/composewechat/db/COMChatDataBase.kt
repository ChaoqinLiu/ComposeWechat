package com.lcq.composewechat.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcq.composewechat.ComposeWechatApp
import com.lcq.composewechat.dao.ChatSmsDao
import com.lcq.composewechat.entitys.ChatSmsEntity

/**
 * author: liuchaoqin
 * 创建时间：2023/12/26
 * Describe ：
 */
@Database(
    entities = [ChatSmsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class COMChatDataBase: RoomDatabase() {

    abstract fun chatSmsDao(): ChatSmsDao

    companion object {
        val instance = Room.databaseBuilder(ComposeWechatApp.instance, COMChatDataBase::class.java, "compose_chat_db").build()
    }
}