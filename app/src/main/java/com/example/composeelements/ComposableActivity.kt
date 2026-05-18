package com.example.composeelements

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
// coil for image processing
import coil.compose.AsyncImage
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


        }
    }
}








