package com.wn.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wn.compose.R

@Preview
@Composable
fun EmptyView() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(245,245,245))) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_nocontent),
            contentDescription = null,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}