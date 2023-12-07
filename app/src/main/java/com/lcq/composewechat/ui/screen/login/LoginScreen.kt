package com.lcq.composewechat.ui.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.R
import com.lcq.composewechat.activity.MainActivity
import com.lcq.composewechat.activity.OtherLoginActivity
import com.lcq.composewechat.data.myAvatar
import com.lcq.composewechat.ui.screen.ModalBottomSheetDialog
import com.lcq.composewechat.ui.screen.ProcessDialogComponent
import com.lcq.composewechat.utils.book.autoCloseKeyboard
import com.lcq.composewechat.utils.book.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/12/6
 * Describe ：
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
@Preview
fun LoginScreen() {
    val context = LocalContext.current as Activity
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    var pwdText by remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }
    var isFocus by remember { mutableStateOf(false) }
    var isPwdAuth by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .autoCloseKeyboard()
    ) {
        Scaffold(
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffEDEDED))
                        .padding(innerPadding)
                ) {
                    LazyColumn(contentPadding = innerPadding) {
                        /**
                         * 导航栏到头像的间距
                         */
                        item {
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                        /**
                         * 头像
                         */
                        item {
                            Box(
                                modifier = Modifier.height(70.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = rememberCoilPainter(request = myAvatar),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(70.dp)
                                )
                            }
                        }
                        /**
                         * 手机号
                         */
                        item {
                            Box(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "18230000000",
                                    fontSize = 18.sp
                                )
                            }
                        }
                        /**
                         * 密码输入框
                         */
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .wrapContentHeight(),
                            ) {
                                TextField(
                                    value = pwdText,
                                    onValueChange = {
                                        pwdText = it
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                        cursorColor = Color(0xff5ECC71)
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                        Text(
                                            text = if (isPwdAuth) "密码" else "验证码",
                                            fontSize = 18.sp,
                                            modifier = Modifier.width(90.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth().onFocusChanged {
                                        isFocus = when {
                                            it.isFocused -> true
                                            else -> false
                                        }
                                    },
                                    placeholder = {
                                        Text(
                                            text = if (isPwdAuth) "请填写微信密码" else "请填写验证码",
                                            fontSize = 16.sp,
                                            color = Color(0xff888888),
                                        )
                                    }
                                )}
                        }
                        /**
                         * 获取验证码
                         */
                        item {
                            if (!isPwdAuth) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 110.dp, end = 15.dp, bottom = 4.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = "获取验证码",
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .wrapContentWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xffE1E1E1))
                                            .padding(
                                                start = 10.dp,
                                                top = 2.dp,
                                                end = 10.dp,
                                                bottom = 6.dp
                                            )
                                    )
                                }
                            }
                        }
                        /**
                         * 手机号底部的下划线
                         */
                        item {
                            Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                                CQDivider(thickness = 1.dp, colorId = if (isFocus) R.color.green else  R.color.gray_10)
                            }
                        }
                        item {
                            Text(
                                text = if (isPwdAuth) "用短信验证码登陆" else "密码登陆",
                                fontSize = 14.sp,
                                color = Color(0xff5F6594),
                                modifier = Modifier
                                    .padding(15.dp)
                                    .clickable {
                                        isPwdAuth = !isPwdAuth
                                    }
                            )
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(top = 40.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "登陆",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xff5ECC71))
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 80.dp,
                                            end = 80.dp
                                        )
                                        .clickable {
                                            if (pwdText == "") {
                                                context.toast(if (isPwdAuth) "请输入密码" else "请输入验证码")
                                                return@clickable
                                            }
                                            if (pwdText != "123456") {
                                                context.toast(if (isPwdAuth) "密码不正确" else "验证码不正确")
                                                return@clickable
                                            }
                                            loading.value = true
                                            scope.launch {
                                                delay(2000)
                                                loading.value = false
                                                MainActivity.navigate(context)
                                            }
                                        },
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            if (loading.value) {
                                ProcessDialogComponent(
                                    loading = loading,
                                    content = "正在登陆..."
                                )
                            }
                        }
                    }
                    /**
                     * 底部布局（找回密码等）
                     */
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            /**
                             * 中间的分割线
                             */
                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(top = 60.dp)
                            ) {
                                Text(
                                    text = "找回密码",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(top = 15.dp, end = 15.dp),
                                )
                                Box(modifier = Modifier
                                    .height(50.dp)
                                    .width(0.5.dp)
                                    .padding(top = 15.dp, bottom = 8.dp)
                                    .background(Color(0xff888888))
                                )
                                Text(
                                    text = "紧急结冻",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier.wrapContentWidth()
                                        .padding(top = 15.dp, start = 15.dp, end=15.dp),
                                )
                                Box(modifier = Modifier
                                    .height(50.dp)
                                    .width(0.5.dp)
                                    .padding(top = 15.dp, bottom = 8.dp)
                                    .background(Color(0xff888888))
                                )
                                Text(
                                    text = "更多",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(top = 15.dp, start = 15.dp)
                                        .clickable {
                                            scope.launch { modalBottomSheetState.show() }
                                        },
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
                /**
                 * 底部更多弹窗
                 */
                ModalBottomSheetDialog(
                    titles = listOf("登陆其他帐号","注册","安全问题","反馈问题"),
                    coroutineScope = scope,
                    modalBottomSheetState = modalBottomSheetState,
                    onSelect = {index, title ->
                        Log.d("LoginScreen", "index====$index  title=====$title")
                        // 登陆其他帐号
                        if (index == 0) {
                            OtherLoginActivity.navigate(context)
                        }
                    }
                )
            }
        )
    }
}
