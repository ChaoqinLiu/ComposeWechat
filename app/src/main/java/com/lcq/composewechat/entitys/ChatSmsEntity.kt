package com.lcq.composewechat.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author: liuchaoqin
 * 创建时间：2023/12/26
 * Describe ：
 */
@Entity(tableName = "chatsms")
data class ChatSmsEntity (
    /**
     * 主键，自增id
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    /**
     * 用户id
     */
    @ColumnInfo(name = "userId")
    var userId: Long = 0,
    /**
     * 消息内容
     */
    @ColumnInfo(name = "message")
    var message: String,
    /**
     * 消息内容类型，与枚举类MediaType对应
     */
    @ColumnInfo(name = "mediaType")
    var mediaType: Int = 1,
    /**
     * 消息类型，与枚举类MessageType对应
     */
    @ColumnInfo(name = "messageType")
    var messageType: Int = 0,
    /**
     * 创建时间
     */
    @ColumnInfo(name = "createBy")
    var createBy: Long = 0,
)