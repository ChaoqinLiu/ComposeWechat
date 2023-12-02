package com.lcq.composewechat.ui.book

import androidx.compose.foundation.lazy.LazyListState
import com.lcq.composewechat.data.AddrBookItem

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：
 */
class StickyHeaderState(
    val state: LazyListState = LazyListState(),
    private var hashMap: Map<String, MutableList<AddrBookItem>>
) {
    fun setData(data: Map<String, MutableList<AddrBookItem>>) {
        this.hashMap = data
    }

    suspend fun scrollToItem(initial: String) {
        val (sum, indexOfSelf) = getLeftHeaderIndexByChar(hashMap, initial)
        state.scrollToItem(sum + indexOfSelf)
    }

    private fun getLeftHeaderIndexByChar(
        data: Map<String, MutableList<AddrBookItem>>,
        initial: String
    ): Pair<Int, Int> {
        val keysBeforeList =
            data.keys.takeWhile { it != initial } // 获取输入字母之前的键
        val sum = keysBeforeList.sumOf { data[it]?.size ?: 0 } // 计算目标之前value数量的总和
        //0对应加 0、1加1、2加2。所以获取目标索引相加即可
        val indexOfSelf = data.keys.indexOf(initial) // 获取输入字母之前的键
        return Pair(sum, indexOfSelf)
    }
}