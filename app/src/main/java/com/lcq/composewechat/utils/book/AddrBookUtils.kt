package com.lcq.composewechat.utils.book

import android.util.Log
import com.lcq.composewechat.data.AddrBookItem
import com.lcq.composewechat.data.contactsList
import net.sourceforge.pinyin4j.PinyinHelper

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：
 */
object AddrBookUtils {

    /**
     * 处理通讯录列表数据，按字母排序
     */
    fun getContactMap(): Map<String, MutableList<AddrBookItem>> {
        val originalHashMap = HashMap<String, MutableList<AddrBookItem>>()
        contactsList.forEach { row ->
            val char = PinyinHelper.toHanyuPinyinStringArray(row.name.first())
            //获取首字母例如：小美xiaomei 结果为x，并转为大写
            val key = char[0][0].uppercase()
            //进行添加到Map中，如果存在，存储到当前key对应的value集合中。否则新建key进行存储
            //使用 getOrPut 函数简化添加到 Map 中的逻辑
            originalHashMap.getOrPut(key) { ArrayList() }.add(row)
        }
        //根据字母进行排序
        val sortedLinkedHashMap = originalHashMap
            .toSortedMap(compareBy { it })
            .toMap()
        // 打印结果
        sortedLinkedHashMap.forEach { (key, value) ->
            Log.d("AddrBookUtils", "$key: $value")
        }
        return sortedLinkedHashMap
    }
}