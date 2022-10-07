package com.softroniiks.airmonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softroniiks.airmonitor.models.Routes
import com.softroniiks.airmonitor.ui.theme.AirMonitorTheme

class MainActivityTwo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirMonitorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewFour() {
    AirMonitorTheme {
        Navigation()
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {
        composable(route = Routes.SPLASH_SCREEN) {
            SplashScreen()
        }

        composable(route = Routes.BOARDING_SCREEN) {
            BoardingScreen(navController = navController)
        }
    }
}

@Composable
fun SplashScreen() {
    val painter = painterResource(id = R.drawable.ic_launcher_background)
    val animate = remember {
        mutableStateOf("")
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.background(Color.Blue)) {
        Image(painter = painter, contentDescription = "splash image")
    }

}

@Composable
fun BoardingScreen(navController: NavController) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column() {

            Button(onClick = {
                navController.navigate("LogIn")
            }) {
                Text(text = "Log in")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Sign up")
            }
        }
    }
}