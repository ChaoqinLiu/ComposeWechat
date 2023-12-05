package com.lcq.composewechat.models

import android.os.Parcelable
import com.lcq.composewechat.enums.ChatAlign
import com.lcq.composewechat.enums.ChatType
import kotlinx.parcelize.Parcelize

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */

@Parcelize
class ChatSession(
    val avatar: String,
    val name: String,
    val message: String,
    val messageType: ChatType,
    val chatAlign: ChatAlign
    ):  Parcelable