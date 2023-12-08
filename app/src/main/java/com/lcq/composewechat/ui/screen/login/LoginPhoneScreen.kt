package com.lcq.composewechat.ui.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lcq.composewechat.CQDivider
import com.lcq.composewechat.R
import com.lcq.composewechat.activity.MainActivity
import com.lcq.composewechat.ui.screen.ProcessDialogComponent
import com.lcq.composewechat.utils.autoCloseKeyboard
import com.lcq.composewechat.utils.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * author: liuchaoqin
 * 创建时间：2023/12/7
 * Describe ：手机号登陆
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPhoneScreen(navController: NavHostController, phone: String) {
    val context = LocalContext.current as Activity
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    var phoneText by remember { mutableStateOf("+86$phone") }
    var pwdText by remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }
    var isPwdAuth by remember { mutableStateOf(true) }
    var isFocus by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .autoCloseKeyboard()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                //返回上一页
                                navController.popBackStack()
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    LazyColumn(contentPadding = innerPadding) {
                        /**
                         * 导航栏到title的间距
                         */
                        item {
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                        /**
                         * 手机号登陆title
                         */
                        item {
                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "手机号登陆",
                                    fontSize = 20.sp
                                )
                            }
                        }
                        /**
                         * 手机号输入框
                         */
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .wrapContentHeight(),
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = phoneText,
                                    onValueChange = {
                                        phoneText = it
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    leadingIcon = {
                                        Text(
                                            text = "手机号",
                                            fontSize = 18.sp,
                                            modifier = Modifier.width(90.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )}
                        }
                        /**
                         * 手机号底部的下划线
                         */
                        item {
                            Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                                CQDivider(thickness = 0.5.dp)
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
                        /**
                         * 进度框
                         */
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
                     * 底部布局（登陆按钮，找回密码等）
                     */
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()) {
                            Box(
                                modifier = Modifier
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
                            /**
                             * 中间的分割线
                             */
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp)
                            ) {
                                Text(
                                    text = "找回密码",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.End
                                )
                                Box(modifier = Modifier
                                    .height(50.dp)
                                    .width(0.5.dp)
                                    .padding(top = 15.dp, bottom = 8.dp)
                                    .background(Color(0xff888888))
                                )
                                Text(
                                    text = "更多选项",
                                    fontSize = 16.sp,
                                    color = Color(0xff5F6594),
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}