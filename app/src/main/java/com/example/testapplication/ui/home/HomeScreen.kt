package com.example.testapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun HomeScreen() {
    TestApplicationTheme {
        Column {
            QuickMenu()
            TodaySection()
        }
    }
}

@Composable
fun QuickMenu() {
    val quickLinkList = listOf(
        QuickLink(Icons.Filled.Face, "Get a coach"),
        QuickLink(Icons.Rounded.DateRange, "My plan"),
        QuickLink(Icons.Default.ShoppingCart, "My meals"),
        QuickLink(Icons.Default.Settings, "Challenges"),
        QuickLink(Icons.Default.ShoppingCart, "Fitshop"),
        QuickLink(Icons.Default.MailOutline, "Explore")
    )

    LazyRow {
        items(quickLinkList) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(240, 240, 240), shape = RoundedCornerShape(15.dp))
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "ok",
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                }
                Text(
                    text = item.name,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )
            }
        }
    }
}

@Composable
fun TodaySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F8FC))
            .padding(top = 15.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) {
        TrackFoodCard()

    }
}

@Composable
fun TrackFoodCard(){
    val todayCalories = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Green,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("1700")
        }
        append(" kacl remaining")
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)
        ) {
            Text(text = "Track you food", fontSize = 12.sp, color = Color.DarkGray)
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = todayCalories,
                fontSize = 12.sp,
                color = Color.Black
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(4.dp),
                progress = 0.2f,
                color = Color.Black,
                strokeCap = StrokeCap.Round,
                backgroundColor = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen()
}

data class QuickLink(var icon: ImageVector, var name: String)