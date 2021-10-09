package com.wn.compose.ui.community

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.wn.compose.widget.EmptyView
import com.wn.compose.widget.TitleBar

@ExperimentalAnimationApi
@Composable
fun CommunityPage(navController: NavController) {
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(bottom = 50.dp)
            .clickable {
                openDialog.value = !openDialog.value
            }
    ) {
        // 标题栏
        TitleBar() {
        }

        EmptyView()

        Popup(alignment = Alignment.BottomStart) {
            // Draw a rectangle shape with rounded corners inside the popup
            AnimatedItemVisibility(openDialog) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Magenta, RoundedCornerShape(0.dp))
                )
            }
        }
    }
}

@Composable
fun showDialog() {
    val openDialog = remember { mutableStateOf(true) }
    val dialogWidth = 200.dp
    val dialogHeight = 50.dp

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Box(
                Modifier
                    .size(dialogWidth, dialogHeight)
                    .background(Color.White)
            )
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
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        )
    ) {
        content()
    }
}

