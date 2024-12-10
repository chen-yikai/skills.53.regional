package com.example.skills53dic.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills53dic.R

@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val pager_images = listOf(
        R.drawable.pager_1,
        R.drawable.pager_2,
    )
    Column(
    ) {
        val pager_state = rememberPagerState { 2 }
        HorizontalPager(
            state = pager_state,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
        ) { page ->
            Image(
                painter = painterResource(pager_images[page]),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Image of page $page"
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp, top = 5.dp)
        ) {
            repeat(2) { i ->
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(if (pager_state.currentPage == i) Color.Gray else Color.LightGray)
                        .height(12.dp)
                        .animateContentSize()
                        .width(if (pager_state.currentPage == i) 35.dp else 12.dp)
                )
            }
        }
    }
}