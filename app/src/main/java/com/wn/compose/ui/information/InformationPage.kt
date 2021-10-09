package com.wn.compose.ui.information

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wn.compose.R
import com.wn.compose.widget.EmptyView
import com.wn.compose.widget.TitleBar

var bgList: List<String>? = null
var headList: List<String>? = null

@Composable
fun InformationPage(navController: NavController, context: Activity) {
    val state = rememberSaveable() {
        mutableStateOf(true)
    }

    val pagePos = rememberSaveable() {
        mutableStateOf(0)
    }

    if (state.value) {
        bgList = getBgImgList()
        headList = getHeadImgList()

        state.value = false
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

        Box(modifier = Modifier.background(color = Color(245,245,245))) {
            if (pagePos.value == 0){
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.recommend_free),
                            contentDescription = null
                        )

                        Image(
                            painter = painterResource(id = R.drawable.recommend_re_back),
                            contentDescription = null
                        )

                        Image(
                            painter = painterResource(id = R.drawable.recommend_follow),
                            contentDescription = null
                        )
                    }

                    Text(
                        text = "热门榜单", fontSize = 16.sp, color = Color.Black,
                        modifier = Modifier.padding(start = 18.dp)
                    )

                    // 热门榜单viewpager
                    HorizontalPagerWithOffsetTransition(context, bgList!!, headList!!)

                    Text(
                        text = "精选推荐", fontSize = 16.sp, color = Color.Black,
                        modifier = Modifier.padding(start = 18.dp, top = 30.dp)
                    )

                    EmptyView()
                }
            } else {
                EmptyView()
            }
        }
    }
}

@Composable
fun getBgImgList(): List<String> {
    val list = mutableListOf<String>()
    for (i in 0..9) {
        list.add(rememberRandomSampleImageUrl(width = 600))
    }
    return list
}

@Composable
fun getHeadImgList(): List<String> {
    val list = mutableListOf<String>()
    for (i in 0..9) {
        list.add(rememberRandomSampleImageUrl())
    }
    return list
}

private val rangeForRandom = (0..100000)

fun randomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}

@Composable
fun rememberRandomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String = remember { randomSampleImageUrl(seed, width, height) }
