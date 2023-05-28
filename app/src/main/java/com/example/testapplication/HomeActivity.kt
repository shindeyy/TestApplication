package com.example.testapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val demoList = listOf(
                DemoItem("Recycler/List view"),
                DemoItem("Recycler/List view"),
                DemoItem("Recycler/List view")
            )
            MyList(list = demoList){
                demoItem ->
                Toast.makeText(applicationContext, "Clicked: ${demoItem.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
fun MyList(list: List<DemoItem>, onItemClick: (DemoItem) -> Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White)) {
        items(list) { item ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 7.dp),
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                ),
                border = BorderStroke(1.dp, Color.Gray)
            ){
                Text(text = item.name, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
                    .clickable { onItemClick(item) })
            }
        }
    }
}

data class DemoItem(val name: String)

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    val demoList = listOf(
        DemoItem("Recycler/List view"),
        DemoItem("Recycler/List view"),
        DemoItem("Recycler/List view")
    )
    MyList(demoList){

    }
}