package com.lcq.composewechat.utils.book

import android.content.Context
import android.widget.Toast

/**
 * author: liuchaoqin
 * 创建时间：2023/12/6
 * Describe ：
 */

fun Context.toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
