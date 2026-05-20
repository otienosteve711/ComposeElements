package com.example.composeelements

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// import get and set value inorder to capture state values
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.composed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
// coil for image processing
import coil.compose.AsyncImage
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class ComposableActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // declarative function to render the user interface
            // basically a function returning ui elements
            composeElementDemo()
        }
    }
    // define the composable function here
    // to mark a function as a composable we use the annotation
    // @composable
    @Composable
    fun composeElementDemo(){
        // define state :
        // state : data to be used / stored by a composable
        var name by remember {
            mutableStateOf("") // this indicates the name can change
        }
        var buttonCount by remember {
            mutableStateOf(0)
        }
        //default list
        val students = listOf(
            "Billy", "mercy", "steve"
        )
        //1. when creating elements we need a container
        // a layout structure
        // modifier - styles a composable unit
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // text composable
            Text( text="Jetpack compose UI showcase" ,
                style= MaterialTheme.typography.headlineMedium)
            // input
            OutlinedTextField(
                value= name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Enter your name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            // showcasing value typed in above composable
            Text(text = "Hello $name")
            // Button
            Button(
                onClick = {
                    buttonCount++ // ref . the count state
                }
            ) {
                Text("Clicked $buttonCount times")
            }
            // Image
            // AsyncImage - coil implementation
            AsyncImage(
                model = "https://picsum.photos/600/300",
                contentDescription = "Demo image",
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation= CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            )
            {
                Text(
                    text="Compose Card",
                    style=MaterialTheme.typography.titleLarge
                )
                // spacer: creates spacing between composables
                Spacer(modifier = Modifier.padding(all=16.dp))
                Text(
                    text = "Cards help group content together"
                )

            }
            //list
            //to do a list we simply loop the composable
            //elements to display our list details
            Text(text="Students List",
                style=MaterialTheme.typography.titleLarge)
            // for the list we use the forEach loop
            students.forEach { students ->
                // for each will action below composable
                // render for each item in the list
                //student references each item in the list
                Card(
                    modifier = Modifier.fillMaxWidth()


                ) {
                    Text(
                        text = students,
                        modifier = Modifier.padding(all = 16.dp)
                    )
                }

            }

            // video : contained in an independent composable function
            Text(text="Video Player",
                style=MaterialTheme.typography.titleLarge)
            // call to a composable unit
            videoPlayer()



        }
    }
    // define the video Player composable
    @Composable
    fun videoPlayer(){
        // context in which it should open
        // context is pointed to the current activity
        val context = LocalContext.current
        // exoplayer :
        // android implementation of a video player
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                // define the src of the video
                val mediaItem = MediaItem.fromUri(
                    Uri.parse(
                        "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
                    )
                )
                // set the media item
                setMediaItem(mediaItem)
                // prepare
                prepare()
                // prevent default playing of video
                playWhenReady=false
            }
        }
        // video player icons
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxWidth().height(220.dp)
        )
    }
    // composable function preview
    // this allows us to see a preview of our screen before running
    @Preview(showBackground = true)
    @Composable
    fun ComposePreview(){
        composeElementDemo()
    }
}








