package com.wn.compose.ui.home

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wn.compose.R
import com.wn.compose.ui.theme.ComposeDemoTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.wn.compose.ui.community.CommunityPage
import com.wn.compose.ui.information.InformationPage
import com.wn.compose.ui.match.MatchPage
import com.wn.compose.ui.mine.MinePage

@ExperimentalAnimationApi
@Composable
fun HomePage(
    context: Activity?,
    homeNavController: NavHostController
) {
    var currentSelectPos by remember { mutableStateOf(0) }

    val tabMatch = ImageBitmap.imageResource(id = R.drawable.tab_match)
    val tabInformation = ImageBitmap.imageResource(id = R.drawable.tab_information)
    val tabCommunity = ImageBitmap.imageResource(id = R.drawable.tab_community)
    val tabMine = ImageBitmap.imageResource(id = R.drawable.tab_mine)

    val tabWidth = (context?.windowManager?.defaultDisplay?.width ?: 0) / 4
    val circlePos = tabWidth / 2 - 50f

    val value by animateFloatAsState(
        targetValue = circlePos + tabWidth * currentSelectPos,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .navigationBarsPadding()// 把系统底部导航栏高度留出来
    ) {
        NavHost(
            navController = navController,
            startDestination = "match",
//            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            composable("match") { MatchPage(homeNavController) }
            composable("information") { InformationPage(navController, context!!) }
            composable("community") { CommunityPage(navController) }
            composable("mine") { MinePage(navController) }
        }

        // bottom navigation view
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(alignment = Alignment.BottomStart)
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        currentSelectPos = 0
                        navController.navigate("match") {
                            // 保存状态，避免每次都创建新的实例
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tab_match),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color(205, 205, 205))
                )

                Text(
                    text = "比赛",
                    modifier = Modifier.padding(bottom = 5.dp),
                    color = if (currentSelectPos == 0) Color(255, 161, 78) else Color(
                        153,
                        153,
                        153
                    ),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        currentSelectPos = 1
                        navController.navigate("information") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tab_information),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color(205, 205, 205))
                )

                Text(
                    text = "资讯",
                    modifier = Modifier.padding(bottom = 5.dp),
                    color = if (currentSelectPos == 1) Color(255, 161, 78) else Color(
                        153,
                        153,
                        153
                    ),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        currentSelectPos = 2
                        navController.navigate("community") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tab_community),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color(205, 205, 205))
                )

                Text(
                    text = "社区", modifier = Modifier.padding(bottom = 5.dp),
                    color = if (currentSelectPos == 2) Color(255, 161, 78) else Color(
                        153,
                        153,
                        153
                    ),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        currentSelectPos = 3
                        navController.navigate("mine") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tab_mine),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color(205, 205, 205))
                )

                Text(
                    text = "我的", modifier = Modifier.padding(bottom = 5.dp),
                    color = if (currentSelectPos == 3) Color(255, 161, 78) else Color(
                        153,
                        153,
                        153
                    ),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Canvas(modifier = Modifier
            .size(40.dp)
            .background(color = Color.Transparent)
            .align(Alignment.BottomStart)
            .padding(bottom = 200.dp)
            .graphicsLayer(
                translationX = value
            ),
            onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = (20.dp).toPx(),
                    style = Fill
                )

                drawCircle(
                    color = Color(255, 161, 78),
                    radius = (14.5.dp).toPx(),
                    style = Fill
                )

                drawImage(
                    image = when (currentSelectPos) {
                        0 -> {
                            tabMatch
                        }
                        1 -> {
                            tabInformation
                        }
                        2 -> {
                            tabCommunity
                        }
                        else -> {
                            tabMine
                        }
                    },
                    topLeft = Offset((11.5.dp).toPx(), ((-8).dp).toPx()),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            })
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true, backgroundColor = 0xFF03DAC5)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    ComposeDemoTheme {
        HomePage(null, navController)
    }
}
