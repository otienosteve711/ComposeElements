package com.example.composeelements
// at the very top of an activity we normally place imports
// imports allow us to use classes / functions
// android / compose tools from other packages which are not
// our own
// the bundle import allows storage and passing of data in android
import android.os.Bundle
// base activity class for compose apps : Jetpack compose functions
import androidx.activity.ComponentActivity
// allows jetpack compose rendering for the UI
import androidx.activity.compose.setContent
// compose layout components: simply what the user sees
// layout components
// arrangement : indicates layout of screen : row or column
import androidx.compose.foundation.layout.Arrangement
// column  : indicates a column arrangement for view
import androidx.compose.foundation.layout.Column
// fillMaxSize : indicates a match parent measurement
// where the compose object occupies entire width and height
// of layout section
import androidx.compose.foundation.layout.fillMaxSize
// Material Design Components
// these are components that have been styled according to
// modern mobile application standards
// materialTheme : base class for getting themes to use our app
import androidx.compose.material3.MaterialTheme
// Surface : indicates canvas to append components to
import androidx.compose.material3.Surface
// Text : a component for showcasing typography with material
// design
import androidx.compose.material3.Text
// Alignment and modifiers imports
// alignment : how content is arranged within a surface
// Modifiers : extra attributes to program how a component is
// displayed
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// an activity in compose is simple the container that hosts
// our user interface (UI) components
// to define an activity we use the class keyword and inherit
// the compose base activity class
// data class to create a student
data class Student(
    val name: String,
    val course: String,
    val studentId: Int
)
class MainActivity : ComponentActivity(){
    // lifecycle methods
    // onCreate() : runs when the activity is first created
    // or opened.
    // it is used to initialize views and dependencies for
    // the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        // calls the lifecycle implementation.
        super.onCreate(savedInstanceState)
        // inside this lifecycle we can define variables
        // that will be referenced for our rendering purposes
        //  for example  I will be displaying a student with
        // programming languages being covered in coursework
        val programmingLanguage = arrayOf(
            "Kotlin","Java","Python"
        )
        // define a student
        // object
        val student1 = Student(
            "Joseph", "MIT", 4000
        )
        // define setContent : to define the UI
        // that the user sees or renders the data
        setContent {
            // MaterialTheme: to apply material design
            // on my components
            MaterialTheme() {
                // Surface : Container for UI hosting
                Surface(
                    // determine the height / width of container
                    modifier = Modifier.fillMaxSize()
                ){
                    // Column
                    // Ui elements inside are arranged
                    // vertically
                    Column(
                        // center items
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ){
                        // compose elements
                        // text component is used to display txt
                        Text(text = "Student Registry")
                        // displaying from our student object
                        Text(text = "Name : ${student1.name}")
                        Text(text = "Course : ${student1.course}")
                        Text(text = "StudentID: ${student1.studentId}")
                        Text(
                            text = "Language Covered: " +
                                    programmingLanguage[0]
                        )

                    }

                }
            }
        }



    }

}