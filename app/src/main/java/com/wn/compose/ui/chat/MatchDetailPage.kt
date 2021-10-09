package com.wn.compose.ui.chat

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.wn.compose.R
import com.wn.compose.ui.community.AnimatedItemVisibility
import com.wn.compose.widget.EmptyView

/**
 * 聊天页面
 */
@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalComposeUiApi
@Composable
fun MatchDetailPage(homeNavController: NavHostController) {
    // 关闭软键盘
    val keyboardController = LocalSoftwareKeyboardController.current
    // 清除焦点
    val focusManager = LocalFocusManager.current

    val openDialog = remember { mutableStateOf(false) }

    fun closeKeyBoard() {
        focusManager.clearFocus(true)
        keyboardController?.hide()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                closeKeyBoard()
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Image(
                painter = painterResource(id = R.drawable.real_match_header_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.match_placard_background_bottom),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.Center)
            )

            Image(painter = painterResource(id = R.drawable.main_bar_back),
                contentDescription = null,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 20.dp, top = 20.dp)
                    .clickable { homeNavController.popBackStack() })
        }

        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            Divider(color = Color(220, 220, 220), thickness = 0.5.dp)

            Box(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .navigationBarsWithImePadding()
                        .padding(10.dp)
                        .padding(end = 50.dp)
                        .height(35.dp)
                        .fillMaxWidth()
                        .background(Color(220, 220, 220), CircleShape)
                        .padding(start = 10.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            closeKeyBoard()
                        }
                    ),
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (text.isBlank()) {
                                    Text(text = "说点什么吧~", color = Color.Gray, fontSize = 14.sp)
                                }
                                innerTextField()
                            }
                        }
                    }
                )

                Image(painter = painterResource(id = R.drawable.real_match_gift),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 13.dp, end = 10.dp)
                        .size(30.dp)
                        .clickable {
                            closeKeyBoard()
                            openDialog.value = true
                        })
            }

            Popup(alignment = Alignment.BottomStart,
                onDismissRequest = {
                    openDialog.value = false
                }) {
                // Draw a rectangle shape with rounded corners inside the popup
                AnimatedItemVisibility(openDialog) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .background(Color.White, RoundedCornerShape(0.dp))
                    ) {
                        Divider(color = Color(220, 220, 220), thickness = 0.5.dp)

                        Column() {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(80.dp)
                                    .border(
                                        0.5.dp,
                                        color = Color(253, 172, 57),
                                        RoundedCornerShape(0.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.real_match_gift_jue_sha),
                                        contentDescription = null
                                    )

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Text(text = "送出绝杀", fontSize = 12.sp, color = Color.Black)

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(modifier = Modifier.wrapContentSize()) {
                                        Text(text = "100", fontSize = 10.sp, color = Color.Gray)

                                        Spacer(modifier = Modifier.width(5.dp))

                                        Image(
                                            painter = painterResource(id = R.drawable.icon_money),
                                            contentDescription = null,
                                            modifier = Modifier.size(10.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .height(60.dp)
                        ) {
                            Divider(color = Color(220, 220, 220), thickness = 0.5.dp)

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_money),
                                    contentDescription = null,
                                    modifier = Modifier.size(10.dp)
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(text = "100", fontSize = 10.sp, color = Color.Black)

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(text = "充值", fontSize = 12.sp, color = Color.Black)


                            }
                        }
                    }
                }
            }
        }
    }
}