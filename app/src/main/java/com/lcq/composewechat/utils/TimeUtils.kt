package github.leavesczy.compose_chat.base.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * author: liuchaoqin
 * 创建时间：2023/12/9
 * Describe ：时间工具类
 */
internal object TimeUtils {

    const val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

    private val format_HH_mm = SimpleDateFormat("HH:mm", Locale.CHINA)

    private val format_MM_dd = SimpleDateFormat("MM-dd", Locale.CHINA)

    private val format_MM_dd_HH_mm = SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)

    fun formatTime(time: Long, format: String): String {
        return SimpleDateFormat(format, Locale.CHINA).format(Date(time))
    }

    fun toTalkTime(timeStamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp * 1000
        val date = calendar.time

        /**
         * 今天凌晨
         */
        val tempCalendar = Calendar.getInstance()
        tempCalendar[Calendar.HOUR_OF_DAY] = 0
        tempCalendar[Calendar.MINUTE] = 0
        tempCalendar[Calendar.MILLISECOND] = 0
        if (calendar.after(tempCalendar)) {
            return format_HH_mm.format(date)
        }
        /**
         * 昨天凌晨
         */
        tempCalendar.add(Calendar.DATE, -1)
        if (calendar.after(tempCalendar)) {
            return "昨天 " + format_HH_mm.format(date)
        }
        /**
         * 前天凌晨
         */
        tempCalendar.add(Calendar.DATE, -1)
        if (calendar.after(tempCalendar)) {
            return "前天"
        }
        return format_MM_dd.format(date)
    }

    fun toChatTime(timeStamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp * 1000
        val date = calendar.time
        /**
         * 今天凌晨
         */
        val tempCalendar = Calendar.getInstance()
        tempCalendar[Calendar.HOUR_OF_DAY] = 0
        tempCalendar[Calendar.MINUTE] = 0
        tempCalendar[Calendar.MILLISECOND] = 0
        if (calendar.after(tempCalendar)) {
            return format_HH_mm.format(date)
        }
        /**
         * 昨天凌晨
         */
        tempCalendar.add(Calendar.DATE, -1)
        if (calendar.after(tempCalendar)) {
            return "昨天 " + format_HH_mm.format(date)
        }
        /**
         * 前天凌晨
         */
        tempCalendar.add(Calendar.DATE, -1)
        if (calendar.after(tempCalendar)) {
            return "前天 " + format_HH_mm.format(date)
        }
        return format_MM_dd_HH_mm.format(date)
    }


    /**
     * 获取当前10位时间戳
     */
    fun currentTimeMillis() :Long {
        return System.currentTimeMillis() / 1000
    }

}