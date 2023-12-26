package com.lcq.composewechat.models

import android.os.Parcelable
import com.lcq.composewechat.enums.MessageType
import com.lcq.composewechat.enums.MediaType
import kotlinx.parcelize.Parcelize

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */

@Parcelize
class ChatSession(
    val userId: Long,
    val avatar: String,
    val name: String,
    val message: String,
    val mediaType: MediaType,
    val messageType: MessageType,
    val createBy: Long
    ):  Parcelable