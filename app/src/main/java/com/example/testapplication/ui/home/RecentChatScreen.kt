package com.example.testapplication.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.testapplication.R

@Composable
fun ChatScreen() {
    Column {
        Text(
            text = "Group that might interest you",
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 10.dp, top = 16.dp),
            fontWeight = FontWeight.Medium
        )
        LazyRow {
            items(8) {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 5.dp, end = 10.dp)
                ) {
                    val (image, groupName) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.ic_bike),
                        contentDescription = "koko",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .border(
                                BorderStroke(1.dp, Color.Gray),
                                CircleShape
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                            .constrainAs(image) {
                                top.linkTo(parent.top, margin = 10.dp)
                            }
                    )
                    Text(text = "Pune fitness",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .constrainAs(groupName) {
                                top.linkTo(image.bottom, margin = 10.dp)
                            },
                        fontWeight = FontWeight.Normal)
                }
            }
        }
        LazyColumn {
            items(10) {
                ConstraintLayout(
                    modifier = Modifier
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
                                CircleShape
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            }
                    )
                    Column(modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                            start.linkTo(image.end, margin = 10.dp)
                        }) {
                        Text(text = "Yogesh Shinde", color = Color.Black, fontSize = 14.sp)
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