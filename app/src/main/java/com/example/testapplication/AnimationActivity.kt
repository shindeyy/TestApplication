package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class AnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sizeState by remember { mutableStateOf(200.dp) }
            var infiniteTransition = rememberInfiniteTransition(label = "")
            val color by infiniteTransition.animateColor(
                initialValue = Color.Gray,
                targetValue = Color.Red,
                animationSpec = infiniteRepeatable(
                    tween(1000),
                    repeatMode = RepeatMode.Reverse
                ), label = ""
            )
            val size by animateDpAsState(
                targetValue = sizeState,
                spring(
                    Spring.DampingRatioMediumBouncy
                ), label = ""
                /*tween(
                    3000,
                    300,
                    FastOutLinearInEasing
                )*/
            )
            Box(modifier = Modifier
                .size(size)
                .background(color),
            contentAlignment = Alignment.Center) {
                Button(onClick = {
                    sizeState += 50.dp
                }) {
                    Text("Click")
                }
            }
        }
    }
}
