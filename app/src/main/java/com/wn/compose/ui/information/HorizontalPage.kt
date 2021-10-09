package com.wn.compose.ui.information

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(context: Activity, bgImgList: List<String>, headImgList: List<String>) {
    val pagerState = rememberPagerState(
        pageCount = 10,
        initialPage = 2,
        initialOffscreenLimit = 2,
    )

    val cardWidth = context?.windowManager?.defaultDisplay?.width!! * 0.9

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 10.dp),
        reverseLayout = true
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = if (pagerState.currentPage - 1 == page) 0.95f else 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
//                        scaleY = scale
                    }

                    translationX = lerp(
                        start = -cardWidth.toFloat() * pageOffset,
                        stop = 0f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                    translationY = lerp(
                        start = 20f * pageOffset,
                        stop = 0f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

//                    alpha = lerp(
//                        start = 0.5f,
//                        stop = 1f,
//                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                    )
                }
                .fillMaxWidth(0.9f)
//                .aspectRatio(1f)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(
                        data = bgImgList[page],
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(160.dp)
                )

                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .offset {
                            val pageOffset =
                                this@HorizontalPager.calculateCurrentOffsetForPage(page)
                            IntOffset(
                                x = (36.dp * pageOffset).roundToPx(),
                                y = 0
                            )
                        },
                    shape = CircleShape,
                    border = BorderStroke(4.dp, MaterialTheme.colors.surface)
                ) {
                    Image(
                        painter = rememberImagePainter(headImgList[page]),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                    )
                }
            }
        }
    }
 }
