package com.wn.compose.ui.match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.wn.compose.widget.SwipeRefreshLoadMore
import com.wn.compose.widget.TitleBar
import androidx.paging.compose.items
import com.wn.compose.R
import com.wn.compose.bean.MatchListBean
import com.wn.compose.lazyMovieItems
import com.wn.compose.viewmodel.MatchViewModel
import com.wn.compose.widget.EmptyView
import kotlinx.coroutines.flow.Flow

@Composable
fun MatchPage(navController: NavController) {
    val pagePos = rememberSaveable() {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        // 标题栏
        TitleBar() {
            pagePos.value = it
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(245, 245, 245))
        ) {
            if (pagePos.value == 0) {
                SwipeRefreshLoadMore(collectAsLazyPagingItems = lazyMovieItems!!) {
                    items(lazyMovieItems!!) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(
                                    0.5.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clip(shape = RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.navigate("match_detail") {
                                        // 保存状态，避免每次都创建新的实例
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = it!!.matchType,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .wrapContentWidth(),
                                    fontSize = 14.sp
                                )

                                Row(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .align(alignment = Alignment.CenterEnd)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.event_icon_data_1),
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.event_icon_data_2),
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.event_icon_data_3),
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                }
                            }

                            Divider(color = Color.LightGray, thickness = 0.5.dp)

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = it!!.teamA_name,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                            .align(
                                                Alignment.TopCenter
                                            )
                                    )

                                    Text(
                                        text = it!!.teamB_name,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .align(
                                                Alignment.BottomCenter
                                            )
                                    )
                                }

                                Text(
                                    text = it!!.matchTime,
                                    fontSize = 14.sp
                                )

                                Box(modifier = Modifier.fillMaxSize()) {
                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(top = 10.dp)
                                    ) {
                                        Text(
                                            text = it!!.teamA_ban,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 18.dp)
                                        )
                                        Text(
                                            text = it!!.teamA_dian,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 20.dp)
                                        )
                                        Text(
                                            text = it!!.teamA_score,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 10.dp)
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(bottom = 10.dp)
                                    ) {
                                        Text(
                                            text = it!!.teamB_ban,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 18.dp)
                                        )
                                        Text(
                                            text = it!!.teamB_dian,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 20.dp)
                                        )
                                        Text(
                                            text = it!!.teamB_score,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 10.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                EmptyView()
            }
        }
    }
}

