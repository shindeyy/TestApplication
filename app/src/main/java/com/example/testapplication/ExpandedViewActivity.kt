package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme

class ExpandedViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                ExpandedCollapseCard()
            }
        }
    }
}

@Composable
fun ExpandedCollapseCard() {
    val fontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_bold, FontWeight.Bold)
    )
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .clickable {
                expanded = !expanded
            }
            .background(Color.White)) {
            Image(painter = painterResource(id = R.drawable.ic_bike), contentDescription = "Ok", contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxWidth()
                .height(300.dp))
            AnimatedVisibility(expanded) {
                Text(modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    TestApplicationTheme {
        ExpandedCollapseCard()
    }
}