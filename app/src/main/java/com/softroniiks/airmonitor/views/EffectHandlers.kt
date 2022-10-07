package com.softroniiks.airmonitor.views

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.softroniiks.airmonitor.Screen
import com.softroniiks.airmonitor.models.Routes
import com.softroniiks.airmonitor.ui.theme.AirMonitorTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class TextFields : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirMonitorTheme {
                Surface() {
                    // A surface container using the 'background' color from the theme

                    val size = remember { mutableStateOf(200) }
                    var sizeState by remember { mutableStateOf(100.dp) }

                    val sizeAnimate by animateDpAsState(targetValue = sizeState)

                    Box(
                        modifier = Modifier
                            .size(sizeState)
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center //align content in the box center
                    ) {
                        Button(onClick = { sizeState += 50.dp }) {
                            Text(text = "Animate")
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewThree() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color.White
    ) {
        Row() {
            Column {
                val size = remember { mutableStateOf(200) }
                var sizeState by remember { mutableStateOf(200.dp) }

                //Animation
                val sizeAnimate by animateDpAsState(
                    targetValue = sizeState,
                    //tween(4000, 300, LinearEasing)
                    spring(Spring.DampingRatioHighBouncy)
                    //keyframes
                )

                val infiniteTransition = rememberInfiniteTransition()
                val color by infiniteTransition.animateColor(
                    initialValue = Color.Blue,
                    targetValue = Color.Green,
                    animationSpec = infiniteRepeatable(
                        tween(2000),
                        repeatMode = RepeatMode.Reverse

                    )
                )

                Box(
                    modifier = Modifier
                        .size(sizeAnimate)
                        .background(color),
                    contentAlignment = Alignment.Center //align content in the box center
                ) {
                    Button(onClick = { sizeState += 50.dp }) {
                        Text(text = "Animate")
                    }
                }

                Navigation()

            }
        }
    }
}


@Composable
fun Form() {
    SideEffect {

    }
    var counter: Int = 0 //non compose code

    LaunchedEffect(key1 = counter) {

    }
}

@Composable
fun CircularProgressBar(
    percentage: Float, number: Int, fontSize: TextUnit, radius: Dp, color: Color,
    strokeWidth: Dp, animDuration: Int, animDelay: Int
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val result = if (animationPlayed) percentage else 0f

    val curPercentage = animateFloatAsState(
        targetValue = result,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true;
    }
}


//for each screen composable block with a corresponding route
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        //INPUT SCREEN
        composable(route = Screen.InputScreen.route) {
            InputScreen("Damola") //component
        }

        //DETAIL SCREEN
        //?optional arg ?name={name},age={age}
        //==/{name} --> mandatory argument that has to be passed to the screen
        val routeWithArguments = "${Routes.DETAIL_SCREEN}/{name}"

        composable(
            route = routeWithArguments, //passing args to route or screen.. Screen.DetailScreen.route + "/{name}/"
            arguments = listOf(
                navArgument("name") {
                    defaultValue = "Damola"
                    type = NavType.StringType
                },
                navArgument("age") {
                    defaultValue = "20"
                },

                )
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable()
fun MainScreen(navController: NavController) {
    val nameInput = remember {
        mutableStateOf("")
    }

    var ageInput by remember { mutableStateOf("") }
    val encodedUrl = URLEncoder.encode(
        "${Routes.DETAIL_SCREEN}/${nameInput.value}/",
        StandardCharsets.UTF_8.toString()
    )

    Row() {
        Column() {

            TextField(label = { Text(text = "name") },
                value = nameInput.value.trim(),
                onValueChange = { newVal ->
                    nameInput.value = newVal.trim()
                })

            Spacer(modifier = Modifier.height(10.dp))

            //navigate to input screen
            Button(onClick = {
                navController.navigate(Routes.INPUT_SCREEN)
            }) {
                Text("View input screen")
            }

            Spacer(modifier = Modifier.height(10.dp))

            //navigate to detail screen, and pass args to route
            Button(onClick = {
                if (nameInput.value != "") {
                    navController.navigate("${Routes.DETAIL_SCREEN}/${nameInput.value}")
                } else {
                    println("The name field is empty")
                }
            }) {
                Text("View details")
            }
        }

        Box(modifier = Modifier.fillMaxHeight()) {

        }
    }
}

@Composable()
fun DetailScreen(name: String?) {
    Row() {
        Column() {
            Text(text = "Welcome $name")
        }
    }
}

@Composable()
fun InputScreen(name: String?) {
    Row() {
        Column() {
            Text(text = "Input Screen ${name}")
        }
    }
}
