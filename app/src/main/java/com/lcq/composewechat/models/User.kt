package com.lcq.composewechat.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * author: liuchaoqin
 * 创建时间：2023/12/21
 * Describe ：
 */

@Parcelize
class User(
    val userId: Long,
    val name: String,
): Parcelable