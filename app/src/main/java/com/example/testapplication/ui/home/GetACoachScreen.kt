package com.example.testapplication.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.testapplication.R

@Composable
fun GetACoachScreen() {
    val modifier = Modifier.height(140.dp)
    Column {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Text(
                    text = "Recently viewed",
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
                    items(4) {
                        Card(
                            modifier = Modifier.padding(
                                start = if (it == 0) 8.dp else 8.dp,
                                end = 8.dp
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 1.dp
                            )
                        ) {
                            ConstraintLayout(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(start = 16.dp, bottom = 16.dp, top = 10.dp)
                            ) {
                                val (image, name) = createRefs()
                                Image(
                                    painter = painterResource(id = R.drawable.ic_bike),
                                    contentDescription = "koko",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(50.dp)
                                        .border(
                                            BorderStroke(1.dp, Color.Gray),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(2.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .constrainAs(image) {
                                            top.linkTo(parent.top)
                                        }
                                )
                                Column(modifier = Modifier
                                    .constrainAs(name) {
                                        top.linkTo(image.top)
                                        bottom.linkTo(image.bottom)
                                        start.linkTo(image.end, margin = 10.dp)
                                        end.linkTo(parent.end, margin = 16.dp)
                                    }) {
                                    Text(
                                        text = "Yogesh Shinde",
                                        color = Color.Black,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "Personal details & account settings",
                                        color = Color.Gray,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
        LazyVerticalGrid(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2),
            content = {
                items(15) {
                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 10.dp)
                        ) {
                            val (image, name, coached, slot) = createRefs()
                            Image(
                                painter = painterResource(id = R.drawable.ic_bike),
                                contentDescription = "Okk",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .constrainAs(image) {
                                        top.linkTo(parent.top, margin = 16.dp)
                                        start.linkTo(parent.start, margin = 16.dp)
                                        end.linkTo(parent.end, margin = 16.dp)
                                    }
                            )
                            Text(text = "Rishi porwan",
                                color = Color.Black,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .constrainAs(name) {
                                        top.linkTo(image.bottom, margin = 10.dp)
                                        start.linkTo(image.start)
                                        end.linkTo(image.end)
                                    })
                            Text(text = "Coached 1.6k people",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .constrainAs(coached) {
                                        top.linkTo(name.bottom, margin = 10.dp)
                                        start.linkTo(name.start)
                                        end.linkTo(name.end)
                                    })

                            Text(text = "1 slot available",
                                color = Color.Gray,
                                fontSize = 10.sp,
                                modifier = Modifier
                                    .constrainAs(slot) {
                                        top.linkTo(coached.bottom, margin = 10.dp)
                                        start.linkTo(coached.start)
                                        end.linkTo(coached.end)
                                    })
                        }
                    }
                }
            })
    }
}