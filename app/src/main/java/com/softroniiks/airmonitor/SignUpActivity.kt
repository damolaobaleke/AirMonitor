package com.softroniiks.airmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airmonitor.ui.theme.AirMonitorTheme
import com.squareup.picasso.Picasso

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirMonitorTheme {
                Greeting(Message("Roland", "Two to tango"))
            }
        }

    }
}

@Composable
fun Greeting(msg: Message) {
    //Text(text = "Hello $name!")
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Column {
            Image(
                painter = painterResource(R.drawable.ic_menu_camera),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(30.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, color = Color(0xFFE91E63), CircleShape)
            )
        }

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            // Add a horizontal space between the author text and book title
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = msg.title,
                style = MaterialTheme.typography.body2
            )
        }

        Column {
            Text(text = msg.author)
            Spacer(modifier = Modifier.height(8.dp))
            Surface(shape = MaterialTheme.shapes.medium,color = Color.Yellow, elevation = 1.dp) {
                Text(
                    text = msg.title,
                    Modifier.padding(start =2.dp,end = 2.dp , top=2.dp, bottom=2.dp),
                    color = Color(0xFFE91E63)
                )
            }
        }

    }
}

//Message Object
data class Message(val author:String, val title:String)

@Preview(name="Sign Up screen" ,showBackground = true)
@Composable
fun DefaultPreview() {
    AirMonitorTheme {
        Greeting(Message("Roland","Two to tango"))
    }
}