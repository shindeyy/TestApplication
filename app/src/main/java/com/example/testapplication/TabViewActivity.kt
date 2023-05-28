package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme

class TabViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabView()
        }
    }
}

@Composable
fun TabView() {
    val tabs = listOf(
        TabItem("Home", Icons.Filled.Home),
        TabItem("Favorites", Icons.Filled.Favorite),
        TabItem("Profile", Icons.Filled.Person),
        TabItem("Add", Icons.Filled.Add)
    )
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column {

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            backgroundColor = Color.White,
            contentColor = Color.Gray,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Black,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .fillMaxWidth()
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.padding(8.dp),
                    content = {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = tab.title,
                                modifier = Modifier
                                    .padding(8.dp),
                                color = if (selectedTabIndex == index) Color.Black else Color.Gray
                            )
                        }
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Cyan)
                ) {
                    Text(text = "Tab 1", modifier = Modifier
                        .align(Alignment.Center))
                }
            }
            1 -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Gray)
                ) {
                    Text(text = "Tab 2", modifier = Modifier
                        .align(Alignment.Center))
                }
            }
            2 -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Yellow)
                ) {
                    Text(text = "Tab 3", modifier = Modifier
                        .align(Alignment.Center))
                }
            }
            3 -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Red)
                ) {
                    Text(text = "Tab 4", modifier = Modifier
                        .align(Alignment.Center))
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    TestApplicationTheme {
        TabView()
    }
}

data class TabItem(val title:String, val icon: ImageVector)