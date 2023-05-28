package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.ui.theme.TestApplicationTheme

class HeaderRecyclerView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sectionedItems = ArrayList<SectionedListItem>()
            for (section in 1..5){
                val itemList = ArrayList<Item>()
                for (item in 1..3){
                    itemList.add(Item("$item", "$item Description"))
                }
                sectionedItems.add(SectionedListItem("Section: $section", itemList))
            }

            SectionedStickyRecyclerView(
                items = sectionedItems,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionedStickyRecyclerView(
    items: List<SectionedListItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items.forEachIndexed { index, sectionedItem ->
            stickyHeader {
                Card(
                    modifier = modifier.padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)) {
                        Text(modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 5.dp),text = sectionedItem.sectionTitle, style = TextStyle(color = Color.Black, fontSize = 16.sp))
                    }
                }
            }
            items(sectionedItem.items) { item ->
                val painter = painterResource(id = R.drawable.ic_bike)
                val contentDescription = "Content description"
                ImageCard(painter = painter , contentDescription = contentDescription, title = "Item ${item.name}")
            }
        }
    }
}

data class SectionedListItem(
    val sectionTitle: String,
    val items: List<Item>
)

data class Item(
    val name: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TestApplicationTheme {
        val sectionedItems = listOf(
            SectionedListItem("Section 1", listOf(Item("1", "Ok"), Item("1", "Ok"), Item("1", "Ok"))),
            SectionedListItem("Section 2", listOf(Item("1", "Ok"), Item("1", "Ok"))),
            SectionedListItem("Section 3", listOf(Item("1", "Ok")))
        )
        SectionedStickyRecyclerView(sectionedItems)
    }
}