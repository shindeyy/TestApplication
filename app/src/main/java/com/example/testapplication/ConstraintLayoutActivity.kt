package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.testapplication.ui.theme.TestApplicationTheme

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutContent()
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // Create references for the composables to constrain
        val (button, text, subtext) = createRefs()

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
                .padding(start = 20.dp)
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            "Hi Yogesh Shinde, how are you. What are you doing!!!!", maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(button.top, margin = 16.dp)
                    start.linkTo(button.end)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Hi Jetpack Compose".repeat(50),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(subtext){
                top.linkTo(button.bottom, margin = 16.dp)
                start.linkTo(button.absoluteLeft, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
@Preview
fun preview(){
    ConstraintLayoutContent()
}