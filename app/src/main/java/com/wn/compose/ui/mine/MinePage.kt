package com.wn.compose.ui.mine

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.statusBarsPadding
import com.wn.compose.R
import java.util.*
import kotlin.concurrent.schedule

@ExperimentalAnimationApi
@Composable
fun MinePage(navController: NavController) {
    var buyVisible = remember { mutableStateOf(false) }
    var friendVisible = remember { mutableStateOf(false) }
    var serviceVisible = remember { mutableStateOf(false) }

    Timer().schedule(100) {
        buyVisible.value = true

        Timer().schedule(150) {
            friendVisible.value = true

            Timer().schedule(200) {
                serviceVisible.value = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(245, 245, 245))
            .padding(bottom = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.mine_head),
                contentDescription = "head",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .shadow(
                        elevation = 3.5.dp,
                        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                    ),
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .wrapContentSize()
                    .padding(top = 10.dp)
                    .align(alignment = Alignment.TopEnd),
            ) {
                Row(
                    modifier = Modifier
                        .padding(end = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.personal_message),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp, 20.dp)
                    )

                    Spacer(modifier = Modifier.size(18.dp, 20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.match_setting),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp, 20.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .border(
                            0.5.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)
                        )
                        .clip(shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp))
                        .clickable { }
                ) {
                    Text(
                        text = "个人主页",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 8.dp,
                            end = 5.dp,
                            bottom = 8.dp
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            // 头像
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 15.dp, top = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.head),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "吴亦凡", fontSize = 16.sp, color = Color.White)
                    Text(text = "VIP 2", fontSize = 12.sp, color = Color.White)
                }
            }

            // 币种, 会员热卖
            Column(
                modifier = Modifier
                    .padding(top = 88.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "0.00", color = Color.White, fontSize = 15.sp)
                        Text(text = "金币", color = Color.White, fontSize = 15.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "0.00", color = Color.White, fontSize = 15.sp)
                        Text(text = "银币", color = Color.White, fontSize = 15.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "0", color = Color.White, fontSize = 15.sp)
                        Text(text = "红包", color = Color.White, fontSize = 15.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "0", color = Color.White, fontSize = 15.sp)
                        Text(text = "卡券", color = Color.White, fontSize = 15.sp)
                    }
                }

                // 会员热卖
                Image(
                    painter = painterResource(id = R.drawable.personal_banner),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                        .height(80.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.personal_topup),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "充值中心", fontSize = 14.sp, color = Color(77, 77, 77),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.personal_task),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "任务中心", fontSize = 14.sp, color = Color(77, 77, 77),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.personal_vip),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "会员中心", fontSize = 14.sp, color = Color(77, 77, 77),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AnimatedItemVisibility(
                visible = buyVisible
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(10.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.personal_mybought),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Text(
                        text = "我的购买", fontSize = 16.sp, color = Color(51, 51, 51),
                        modifier = Modifier.padding(start = 30.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                            .align(Alignment.CenterEnd),
                        colorFilter = ColorFilter.tint(Color(128, 138, 135))
                    )

                    Text(
                        text = "36", fontSize = 16.sp, color = Color(51, 51, 51),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                    )
                }
            }

//            Divider(color = Color(128, 118, 105), thickness = 0.5.dp)

            AnimatedItemVisibility(
                visible = friendVisible
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.personal_friend),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Text(
                        text = "邀请好友", fontSize = 16.sp, color = Color(51, 51, 51),
                        modifier = Modifier.padding(start = 30.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                            .align(Alignment.CenterEnd),
                        colorFilter = ColorFilter.tint(Color(128, 138, 135))
                    )

                    Text(
                        text = "邀请好友得3天黄金会员", fontSize = 12.sp,
                        color = Color(153, 153, 153),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                    )
                }
            }

//            Divider(color = Color(128, 118, 105), thickness = 0.5.dp)

            AnimatedItemVisibility(
                visible = serviceVisible
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.personal_srevice),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Text(
                        text = "服务中心", fontSize = 16.sp, color = Color(51, 51, 51),
                        modifier = Modifier.padding(start = 30.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                            .align(Alignment.CenterEnd),
                        colorFilter = ColorFilter.tint(Color(128, 138, 135))
                    )

                    Text(
                        text = "在线客服（9:30-22:00)", fontSize = 12.sp,
                        color = Color(153, 153, 153),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AnimatedItemVisibility(
    visible: MutableState<Boolean>,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        )
    ) {
        content()
    }
}


@ExperimentalAnimationApi
@Preview
@Composable
fun preview() {
    MinePage(rememberNavController())
}

// lazyColumn item动画
//@ExperimentalAnimationApi
//@Suppress("UpdateTransitionLabel", "TransitionPropertiesLabel")
//@SuppressLint("ComposableNaming", "UnusedTransitionTargetStateParameter")
///**
// * @param state Use [updateAnimatedItemsState].
// */
//inline fun <T> LazyListScope.animatedItemsIndexed(
//    state: List<AnimatedItem<T>>,
//    enterTransition: EnterTransition = expandVertically(),
//    exitTransition: ExitTransition = shrinkVertically(),
//    noinline key: ((item: T) -> Any)? = null,
//    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
//) {
//    items(
//        state.size,
//        if (key != null) { keyIndex: Int -> key(state[keyIndex].item) } else null
//    ) { index ->
//
//        val item = state[index]
//        val visibility = item.visibility
//
//        androidx.compose.runtime.key(key?.invoke(item.item)) {
//            AnimatedVisibility(
//                visibleState = visibility,
//                enter = enterTransition,
//                exit = exitTransition
//            ) {
//                itemContent(index, item.item)
//            }
//        }
//    }
//}

