package com.softroniiks.airmonitor

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.airmonitor.ui.theme.AirMonitorTheme
import com.softroniiks.airmonitor.views.AirQualityNavigation
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.ic_launcher_background)
            AirMonitorTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.primary
                ) {
                    Greeting(Message("Roland", "Two to tango"))
                    ImageCard(
                        title = "Sweet Poison", contentDescription = "Hello",
                        painter = painter
                    )
                }

            }
        }

    }
}

@Composable
fun Greeting(msg: Message) {
    //Text(text = "Hello $name!")
    //justify-content-around etc..
    Row(modifier = Modifier.padding(all = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        //column 1
        Column() {
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

        //column 2- text-center
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Red)
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2,
            )

            // Add a horizontal space between the author text and book title
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = msg.title,
                style = MaterialTheme.typography.body2
            )

        }

        //column 3
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var isExpanded = false

            Text(text = msg.author)
            Surface(
                modifier = Modifier.clickable {/*action on clicked*/ },
                shape = MaterialTheme.shapes.medium,
                color = Color.Yellow,
                elevation = 1.dp
            ) {
                Text(
                    text = msg.title,
                    Modifier.padding(start = 2.dp, end = 2.dp, top = 2.dp, bottom = 2.dp),
                    color = Color(0xFFE91E63),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        DisplayComments()

    }


    //Counter(text = "Counter", num = 0 )

}

@Composable
//onAuthorClicked: (String) -> (Unit)
fun Conversation(messages: List<Message>) {
    LazyColumn {
        //for each message display the message in the message card
        items(messages) { message ->
            //Message card component
            MessageCard(message)
        }
    }
}

@Composable
//order of calling property functions are sensitive
// onClicked:  (String) -> (Unit)
//offset(), acts like moving margin but its absolute.
fun MessageCard(message: Message) {
    Card(
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .padding(0.dp)
            .width(60.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .clickable {

                },
            text = message.title,

            )
    }
    Spacer(modifier = Modifier.height(5.dp)) //used as margin
}


@Composable
fun DisplayComments() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val comments = ArrayList<Message>()

        comments.add(Message("Dams", "RXT"))
        comments.add(Message("Lams", "NORD"))
        comments.add(Message("Bolly", "AVG"))

        Conversation(messages = comments)
    }
}

@Composable
fun ImageCard(title: String, contentDescription: String, painter: Painter) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp
    ) {
        //Box --> has a row and column by default
        //With Image
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            ) //centerCrop

            //Gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f
                        )
                    )
            )

            //Text position
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 12.sp))
            }

        }

    }
}

//Conversation(SampleData.conversationSample) takes a list of messages

//Message Object
data class Message(val author: String, val title: String)
//


@Preview(name = "Sign Up (Light Mode)", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Sign Up (Dark Mode)"
)
@Composable
fun DefaultPreview() {
    val painter = painterResource(id = R.drawable.ic_launcher_background)
    val fontFamily = FontFamily(
        Font(R.font.gilroy_bold, FontWeight.Bold),
        Font(R.font.gilroy_regular, FontWeight.Normal),
        Font(R.font.gilroy_medium, FontWeight.Medium),
    )

    AirMonitorTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary
        ) {
            Column {
                Greeting(Message("Roland", "Two to tango"))

                //Take half the  width of the row
                Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                    ImageCard(
                        title = "Sweet Poison", contentDescription = "Hello",
                        painter = painter
                    )
                }

                //
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 50.sp
                            )
                        ) {
                            append("H")
                        }
                        append("ello")
                    },
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline,
                    )
                )


                //RECOMPOSING -STATE
                ColorBox(
                    modifier = Modifier
                        .width(400.dp)
                        .height(200.dp)
                )

                //
                counter()
            }
        }
    }
}


//when you change state or recompose and func is called again,
// the color would be set back to yellow. so to fix, use remember keyword
//REMEMBER -->  means remember the value of the state from the last composition
//so when Box is recomposed don't reset the value to yellow again
@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    val colorState = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(modifier = modifier
        .background(colorState.value)
        .clickable {
            //Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()) //random color
            colorState.value = Color.Red //change to red
        }

    ) {

    }

//    randomBox(Modifier.width(300.dp).height(200.dp)){
//        colorState.value = it
//    }

}


//Takes callback function to update ColorBox components when clicked, works like props in react.js
@Composable
fun randomBox(modifier: Modifier = Modifier, updateColorBoxColor:(color:Color)-> Unit){
    Box(modifier = modifier
        .background(Color.Blue)
        .clickable {
            updateColorBoxColor(
                Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            )
        }
    ){

    }
}

@Composable
fun counter() {
    val numCount = remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .border(2.dp, Color.Black, RectangleShape)
            .fillMaxWidth(1f)
    ) {
        Text(text = numCount.value.toString(), color = Color.Black, fontSize = 20.sp,
            textAlign = TextAlign.Center)
    }

    Row{
        Button(modifier = Modifier
            .background(Color.Red),
            onClick = {  numCount.value++ }) {
            Text(text = "Click me !", color = Color.White )
        }
    }

}

@Composable
private fun loadLogin() {
    val context = LocalContext.current

    val intent = Intent(context, AirQualityNavigation::class.java)
    intent.putExtra("Name", "Damola")
    startActivity(context, intent, null)
}