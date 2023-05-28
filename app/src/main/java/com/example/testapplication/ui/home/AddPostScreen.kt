package com.example.testapplication.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddPostBottomSheet(bottomSheetState: ModalBottomSheetState) {
    val list = listOf(
        AddPost("Start a Discussion"),
        AddPost("Post an Update"),
        AddPost("Post your Transformation"),
        AddPost("Add a Recipe"),
    )
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            LazyColumn(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                items(list) { post ->
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = post.name,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium

                        )
                    }
                    if (post != list.last()) {
                        // check if current item is not the last one
                        Divider(
                            thickness = 1.dp,
                            color = Color.LightGray,
                            modifier = Modifier.padding(start = 3.dp, end = 3.dp)
                        )
                    }
                }
            }
        },
        content = {

        },
        scrimColor = Color.Black.copy(alpha = 0.5f),
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier.zIndex(2f)
    )
}

data class AddPost(val name: String)