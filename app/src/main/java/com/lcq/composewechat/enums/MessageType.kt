package com.lcq.composewechat.enums

/**
 * author: liuchaoqin
 * 创建时间：2023/12/4
 * Describe ：
 */
enum class MessageType(val value: Int) {
    /**
     * 接收的信息
     */
    RECEIVE(0),

    /**
     * 发送的信息
     */
    SEND(1)
}