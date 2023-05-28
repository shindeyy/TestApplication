package com.example.testapplication.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.testapplication.DrawerItem
import com.example.testapplication.R

@Composable
fun MyDrawerScreen() {
    Column {
        LazyColumn {
            item {
                ConstraintLayout(modifier = Modifier.padding(16.dp)) {
                    val (image, name) = createRefs()
                    val rainbowColorsBrush = remember {
                        Brush.sweepGradient(
                            listOf(
                                Color(0xFF9575CD),
                                Color(0xFFBA68C8),
                                Color(0xFFE57373),
                                Color(0xFFFFB74D),
                                Color(0xFFFFF176),
                                Color(0xFFAED581),
                                Color(0xFF4DD0E1),
                                Color(0xFF9575CD)
                            )
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_bike),
                        contentDescription = "kokok",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            }
                            .height(60.dp)
                            .width(60.dp)
                            .border(
                                BorderStroke(2.dp, rainbowColorsBrush),
                                CircleShape
                            )
                            .padding(3.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                            start.linkTo(image.end, margin = 10.dp)
                        }) {
                        Text(text = "Yogesh Shinde", color = Color.Black, fontSize = 16.sp)
                        Text(text = "See your profile", color = Color.Gray, fontSize = 12.sp)
                    }
                }

                ConstraintLayout(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 16.dp, top = 10.dp)
                ) {
                    val (image, name) = createRefs()
                    Icon(imageVector = Icons.Filled.Person,
                        contentDescription = "Okk",
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            })
                    Column(modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                            start.linkTo(image.end, margin = 10.dp)
                        }) {
                        Text(text = "Settings", color = Color.Black, fontSize = 14.sp)
                        Text(
                            text = "Personal details & account settings",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
                Divider(modifier = Modifier.padding(start = 10.dp, end = 10.dp), thickness = 1.dp)
            }
            val list = listOf(
                DrawerItem("Home", Icons.Filled.Home),
                DrawerItem("Search", Icons.Filled.Search),
                DrawerItem("Cart", Icons.Filled.ShoppingCart),
                DrawerItem("Profile", Icons.Filled.Person),
                DrawerItem("Home", Icons.Filled.Home),
                DrawerItem("Search", Icons.Filled.Search)
            )

            itemsIndexed(list) { index, drawerItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(drawerItem.icon, contentDescription = "Menu")
                    Text(text = drawerItem.name, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}