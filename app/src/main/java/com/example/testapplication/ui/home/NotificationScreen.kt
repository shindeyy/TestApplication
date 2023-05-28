package com.example.testapplication.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
fun NotificationScreen() {
    LazyColumn {
        items(7) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, top = 10.dp)
            ) {
                val (image, name, divider) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_bike),
                    contentDescription = "koko",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .border(
                            BorderStroke(2.dp, Color.Gray),
                            RoundedCornerShape(10.dp)
                        )
                        .padding(1.dp)
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
                    }) {
                    Text(text = "Yogesh Shinde", color = Color.Black, fontSize = 14.sp)
                    Text(
                        text = "Personal details & account settings",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Text(text = "10 min ago", color = Color.Gray, fontSize = 10.sp)
                }
            }
            Divider(modifier = Modifier.padding(start = 5.dp, end = 5.dp), thickness = 0.5.dp)
        }
    }
}