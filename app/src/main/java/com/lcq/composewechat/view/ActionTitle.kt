package com.lcq.composewechat.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.lcq.composewechat.R
import com.lcq.composewechat.data.ActionTitleItem
import com.lcq.composewechat.data.HeaderItem

/**
 * author: liuchaoqin
 * 创建时间：2023/12/2
 * Describe ：
 */
interface OnActionClickListener {
    fun onClick()
}

@Composable
fun ActionTitle(it: ActionTitleItem, context: Context, showLine: Boolean, onClickListener: OnActionClickListener) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(50.dp)
            .clickable { onClickListener.onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(25.dp)) {
                Image(
                    painter = painterResource(id = it.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )
            }
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .weight(3f)
                ) {
                    Text(
                        text = it.title,
                        fontSize = 17.sp,
                        color = Color(ContextCompat.getColor(context, R.color.black)),
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(4f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        if (it.image != null && it.image != "") {
                            Image(
                                painter = rememberCoilPainter(request = it.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(35.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                        }
                        Icon(
                            Icons.Filled.ArrowForwardIos, null,
                            modifier = Modifier.size(20.dp),
                            tint = Color(ContextCompat.getColor(context, R.color.gray_10)
                            )
                        )
                    }
                }
            }
        }
    }
    if (showLine) {
        Divider(
            color = Color(ContextCompat.getColor(context, R.color.gray_10)),
            thickness = 0.2.dp,
            modifier = Modifier.padding(start = 70.dp)
        )
    }
}