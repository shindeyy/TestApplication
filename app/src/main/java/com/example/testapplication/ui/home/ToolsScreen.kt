package com.example.testapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ToolsScreen() {
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(5) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(start = 16.dp, bottom = 16.dp, top = 10.dp)
                ) {
                    val (image, name) = createRefs()
                    Icon(imageVector = Icons.Filled.Person,
                        contentDescription = "Okk",
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            })
                    Text(text = "Settings",
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .constrainAs(name) {
                                top.linkTo(image.bottom, margin = 16.dp)
                            })
                }
            }
        }
    })
}