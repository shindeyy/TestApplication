package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListExample()
            //ListIndexed()
        }
    }
}

@Composable
fun ListIndexed(){
    val fontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Thin),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_regular, FontWeight.Normal)
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(
            listOf("Hi","Jetpack", "Compose", "List")
        ){ index, string ->
            Text(text = string,
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp))
        }
    }
}

@Composable
fun ListExample(){
    LazyColumn{
        items(100){
            val painter = painterResource(id = R.drawable.ic_bike)
            val contentDescription = "Content description"
            ImageCard(painter =painter , contentDescription = contentDescription, title = "Item $it")
        }
    }
}

@Composable
fun NormalList(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        for ( i in 0..50){
            Text(text = "Item $i",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp))
        }
    }
}

data class ItemView(val id: Int, val text: String)


