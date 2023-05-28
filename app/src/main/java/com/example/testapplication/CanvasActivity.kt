package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme

class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCanvas()
        }
    }
}

@Composable
fun MyCanvas() {
    Column {
        Canvas(modifier = Modifier
            .padding(20.dp)
            .size(300.dp)){
            drawRect(color = Color.Black, size = size)
            drawRect(color = Color.Red,
                topLeft = Offset(100f, 50f),
                size = Size(100f, 100f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawRect(color = Color.Red,
                topLeft = Offset(600f, 50f),
                size = Size(100f, 100f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Red, Color.Yellow),
                    center = center,
                    radius = 250f
                ),
                radius = 250f
            )
        }

        Canvas(modifier = Modifier
            .padding(20.dp)
            .size(300.dp, 120.dp)){
            drawArc(
                color = Color.Cyan,
                size = Size(300f, 300f),
                startAngle = 0f,
                sweepAngle = 290f,
                useCenter = true
            )
            drawArc(
                color = Color.Green,
                topLeft = Offset(400f, 0f),
                size = Size(300f, 300f),
                startAngle = 0f,
                sweepAngle = 290f,
                useCenter = false,
                style = Stroke(3.dp.toPx())
            )
        }

        Canvas(modifier = Modifier
            .padding(20.dp)
            .size(600.dp, 150.dp)){
            drawOval(
                color = Color.Gray,
                size = Size(200f,300f)
            )
            drawLine(
                color = Color.Magenta,
                start = Offset(250f, 150f),
                end = Offset(500f, 150f),
                strokeWidth = 5.dp.toPx(),
            )
            drawLine(
                color = Color.Magenta,
                start = Offset(380f, 30f),
                end = Offset(380f, 250f),
                strokeWidth = 5.dp.toPx(),
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TestApplicationTheme {
        MyCanvas()
    }
}