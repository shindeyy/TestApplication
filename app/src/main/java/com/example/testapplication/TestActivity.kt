package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.ui.theme.TestApplicationTheme
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.roundToInt

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeightSlider()
                }
            }
        }
    }
}

@Composable
fun TestComposable() {
    var isSecondVisible by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "First View",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            if (isSecondVisible) {
                Text(
                    text = "Second View",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                        .run {
                            if (isSecondVisible) this.padding(end = 8.dp)
                            else this.padding(horizontal = 16.dp)
                        }
                )
            }
        }

        OutlinedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { isSecondVisible = !isSecondVisible }
        ) {
            Text(text = if (isSecondVisible) "Hide Second View" else "Show Second View")
        }

    }
}


@Composable
fun WeightSlider() {
    var weight by remember { mutableStateOf(50f) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weight.roundToInt().toString(),
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .background(Color.White, CircleShape)
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp)
                    .background(Color.White, CircleShape)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures { change, _ ->
                            val maxWeight = 100f
                            val minWeight = 0f
                            val dragY = change.position.y
                            val height = size.height
                            val range = maxWeight - minWeight
                            val pixelsPerValue = height / range

                            val weightValue = min(
                                maxWeight,
                                max(
                                    minWeight,
                                    maxWeight - (dragY / pixelsPerValue)
                                )
                            )

                            weight = weightValue
                        }
                    }
            ) {
                DrawWeightSlider(weight, Modifier.fillMaxHeight())
            }
        }
    }
}

@Composable
fun DrawWeightSlider(weight: Float, modifier: Modifier) {
    Canvas(modifier = modifier) {
        val barWidth = 8.dp.toPx()
        val barHeight = size.height

        val center = Offset(size.width / 2f, (100f - weight) / 100f * barHeight)

        // draw weight points
        val points = (0..10).map { it.toFloat() * 10f }
        val pointRadius = 12.dp.toPx()
        val pointSpacing = barHeight / 10f

        points.forEach { point ->
            val pointY = (100f - point) / 100f * barHeight
            drawCircle(
                if (point == weight) Color.Green else Color.Gray,
                pointRadius,
                Offset(size.width / 2f, pointY),
            )
        }

        // draw slider bar
        drawLine(
            Color.Gray,
            Offset(size.width / 2f - barWidth / 2f, 0f),
            Offset(size.width / 2f - barWidth / 2f, barHeight),
            strokeWidth = barWidth,
        )
        drawLine(
            Color.Gray,
            Offset(size.width / 2f + barWidth / 2f, 0f),
            Offset(size.width / 2f + barWidth / 2f, barHeight),
            strokeWidth = barWidth,
        )

        // draw slider thumb
        drawCircle(
            Color.Green,
            barWidth,
            center,
        )
    }
}

@Preview
@Composable
fun PreviewScreen() {
    TestComposable()
}
