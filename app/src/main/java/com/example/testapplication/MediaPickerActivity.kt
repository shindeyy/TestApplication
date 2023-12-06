package com.example.testapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.testapplication.ui.theme.TestApplicationTheme

class MediaPickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MediaPickerRoot()
                }
            }
        }
    }
}

@Composable
fun MediaPickerRoot(){
    Column (modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp)){
        PickImage()
        PickMultipleImage()
        PickVideo()
        PickDocument()
    }
}

@Composable
fun PickImage(){
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Select Image")
        }

        result.value?.let { image ->
            //Use Coil to display the selected image
            val painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = image)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(150.dp, 150.dp)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun PickMultipleImage(){
    val result = remember { mutableStateOf<List<Uri?>?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Select Multiple Image")
        }

        result.value?.let { images ->
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
                items(images){
                    //Use Coil to display the selected image
                    val painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = it)
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp, 150.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PickVideo(){
    val result = remember { mutableStateOf<List<Uri?>?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(2)) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly)
            )
        }) {
            Text(text = "Select Video")
        }

        result.value?.let { videos ->
            LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
                items(videos){ video->
                    Text(text = "Video Path: "+ video?.path)
                }
            }
        }
    }
}

@Composable
fun PickDocument(){
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(arrayOf("application/pdf"))
        }) {
            Text(text = "Select Document")
        }
        result.value?.let { image ->
            Text(text = "Document Path: "+image.path.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview15() {
    TestApplicationTheme {
        MediaPickerRoot()
    }
}