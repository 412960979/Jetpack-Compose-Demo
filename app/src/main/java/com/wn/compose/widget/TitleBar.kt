package com.wn.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.wn.compose.R

/**
 * 标题栏
 */
@Composable
fun TitleBar(onSelect: (tabPosition: Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color(253, 172, 57))
    ) {
        Image(
            painter = painterResource(id = R.drawable.bar_net), contentDescription = null,
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .padding(end = 20.dp)
                .size(107.dp, 64.dp)
        )

        var selectPos = rememberSaveable() {
            mutableStateOf(0)
        }
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.Center)
        ) {

            Text(
                text = "足球",
                color = if (selectPos.value == 0) Color(253, 172, 57) else Color.White,
                modifier = Modifier
                    .shadow(
                        elevation = 0.5.dp,
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    )
                    .border(
                        0.5.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    )
                    .background(color = if (selectPos.value == 0) Color.White else Color.Transparent)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false)
                    ) {
                        selectPos.value = 0
                        onSelect(selectPos.value)
                    }
                    .padding(8.dp)
            )
            Text(text = "篮球",
                color = if (selectPos.value == 0) Color.White else Color(253, 172, 57),
                modifier = Modifier
                    .border(
                        0.5.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    )
                    .shadow(
                        elevation = 0.5.dp,
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    )
                    .background(color = if (selectPos.value == 0) Color.Transparent else Color.White)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false)
                    ) {
                        selectPos.value = 1
                        onSelect(selectPos.value)
                    }
                    .padding(8.dp))
        }
    }
}

@Preview
@Composable
fun previewTitleBar() {
    TitleBar(
        onSelect = {}
    )
}