package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme

class VisibilityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    VisibilityComposable()
                }
            }
        }
    }
}

@Composable
fun VisibilityComposable() {
    var isSecondVisible by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "First View",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            if (isSecondVisible) {
                Text(text = "Second View",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f).fillMaxWidth().padding(start = 8.dp).run {
                        if (isSecondVisible) this.padding(end = 8.dp)
                        else this.padding(horizontal = 16.dp)
                    })
            }
        }

        OutlinedButton(modifier = Modifier.padding(top = 16.dp),
            onClick = { isSecondVisible = !isSecondVisible }) {
            Text(text = if (isSecondVisible) "Hide Second View" else "Show Second View")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    TestApplicationTheme {
        VisibilityComposable()
    }
}