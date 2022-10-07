package com.softroniiks.airmonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softroniiks.airmonitor.ui.theme.AirMonitorTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TextFields : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirMonitorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold() {

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewTwo() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState()
        var usernameState = remember { mutableStateOf("") }
        var passwordState by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()//get coroutine scope

        //scaffold layout makes it easier to use material UI elements
        Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                OutlinedTextField(
                    label = { Text("username") },
                    value = usernameState.value,
                    onValueChange = {
                        //println(it)
                        usernameState.value = it
                    },
                    singleLine = false,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    println(usernameState.value);
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Submitted ${usernameState.value}")
                    }
                }) {
                    Text("Submit")
                }

                //LISTS
            }
        }

        val index = remember{mutableStateOf(40)}
        val scrollState = rememberScrollState()

        Row() {
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                for (i in 1..index.value){
                    Text(text ="I am $i", fontSize = 18.sp)
                }

                Button(onClick = { index.value--}) {
                    Text(text = "Reduce")
                }
            }

            //items are lazily loaded, only load when scroll to position
            Column() {
                LazyColumn {
                    items(300) { item->
                        Text(text ="I am $item", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun Form() {
    Text(text = "Welcome")
}