package com.lcq.composewechat.data
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sms
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * author: liuchaoqin
 * 创建时间：2023/11/30
 * Describe ：
 */

/**
 * 首页底部导航栏
 */
data class NavigationItem(
    /**
     * 名称
     */
    val title: String,
    /**
     * 图标
     */
    val icon: ImageVector,
)

data class MessageItem(
    val avatar: String,
    val name: String,
    val message: String,
    val lastTime: String,
    val userId: Long,
)

/**
 * 首页标题
 */
val titles = listOf("微信", "通讯录", "发现", "")

/**
 * 底部导航数据
 */
val navList = listOf(
    NavigationItem(
        "微信",
        Icons.Filled.Sms,
    ),
    NavigationItem(
        "通讯录",
        Icons.Filled.Contacts,
    ),
    NavigationItem(
        "发现",
        Icons.Filled.Explore,
    ),
    NavigationItem(
        "我",
        Icons.Filled.Person,
    ),
)

val messageList = listOf(
    MessageItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "小美",
        "好的",
        "12:30",
        1
    ),
    MessageItem(
        "https://pic1.zhimg.com/v2-2c70ede0f052473f32cb19f39772257b_xs.jpg?source=172ae18b",
        "公众号",
        "东华医院周末专家来诊...",
        "11:00",
        2
    ),
    MessageItem(
        "https://img1.duote.com/duoteimg/dtnew_softup_img/202101/de6e4734cc5f4f70.png",
        "QQ邮箱提醒",
        "广告｜六福珠宝会讯12月号",
        "12:30",
        3,
    ),
    MessageItem(
        "https://img1.baidu.com/it/u=1080845142,2144988489&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500",
        "腾讯新闻",
        "地铁普遍亏损背后",
        "13:58",
        4
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "大牛",
        "好的",
        "12:30",
        5
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "小姑",
        "好的",
        "12:30",
        6
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "小花",
        "好的",
        "10:31",
        7
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "好妹仔",
        "真的假的",
        "12:30",
        8
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "梓潼",
        "好的～",
        "12:50",
        9
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "小美",
        "好的",
        "12:30",
        10
    ),
    MessageItem(
        "https://img.duoziwang.com/2021/04/07291624704661.jpg",
        "小明",
        "好的",
        "12:30",
        11
    ),
    MessageItem(
        "https://img.duoziwang.com/2021/04/08260845108757.jpg",
        "小爱",
        "好的",
        "12:30",
        12
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/18/06032028705302.jpg",
        "小阳",
        "好的",
        "12:30",
        13
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "大牛",
        "好的",
        "12:30",
        14
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "小姑",
        "好的",
        "12:30",
        15
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "小花",
        "好的",
        "10:31",
        16
    ),
    MessageItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "好妹仔",
        "真的假的",
        "12:30",
        17
    ),
    MessageItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "梓潼",
        "好的～",
        "12:50",
        18
    ),
)

/**
 * 小程序入口
 */
data class MiniProgramItem(
    /**
     * 名称
     */
    val title: String,
    /**
     * 图标
     */
    val icon: String,
)

val miniProgramList = listOf(
    MiniProgramItem(
        "饿了么",
        "https://img0.baidu.com/it/u=2625005847,2716895016&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        "美图",
        "https://img1.baidu.com/it/u=1752963805,4078506746&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        "滴滴",
        "https://img0.baidu.com/it/u=1068101613,1323308017&fm=253&fmt=auto&app=138&f=PNG?w=200&h=200"
    ),
    MiniProgramItem(
        "青橘单车",
        "https://img0.baidu.com/it/u=195120191,2939897897&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
    MiniProgramItem(
        "斗地主",
        "https://img2.baidu.com/it/u=926635057,1451495262&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
    MiniProgramItem(
        "羊城通",
        "https://img2.baidu.com/it/u=2751300851,4181594410&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        "美图秀秀",
        "https://img1.baidu.com/it/u=417359459,147216874&fm=253&fmt=auto&app=138&f=PNG?w=200&h=200"
    ),
    MiniProgramItem(
        "拼多多",
        "https://img2.baidu.com/it/u=620052409,134315960&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
)

val mineMiniProgramList = listOf(
    MiniProgramItem(
        "羊城通",
        "https://img2.baidu.com/it/u=2751300851,4181594410&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"
    ),
    MiniProgramItem(
        "青橘单车",
        "https://img0.baidu.com/it/u=195120191,2939897897&fm=253&fmt=auto&app=138&f=PNG?w=190&h=190"
    ),
)