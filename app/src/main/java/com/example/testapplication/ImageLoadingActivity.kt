package com.example.testapplication

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.testapplication.ui.theme.TestApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageLoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
            LoadImage(modifier)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        Column {
            GlideImage(
                model = "https://picsum.photos/200",
                contentDescription = "KOoko",
                modifier = modifier,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "Okk",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(CircleShape)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "Okk",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
            )

            val borderWidth = 4.dp
            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "Okk",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .border(
                        BorderStroke(borderWidth, Color.Yellow),
                        CircleShape
                    )
                    .padding(borderWidth)
                    .clip(CircleShape)
            )

            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "kokok",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .border(
                        BorderStroke(borderWidth, rainbowColorsBrush),
                        CircleShape
                    )
                    .padding(borderWidth)
                    .clip(CircleShape)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "koko",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .border(
                        BorderStroke(borderWidth, rainbowColorsBrush),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(borderWidth)
                    .clip(RoundedCornerShape(10.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = "oko",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    val modifier = Modifier
        .size(200.dp)
        .padding(16.dp)
    LoadImage(modifier)
}