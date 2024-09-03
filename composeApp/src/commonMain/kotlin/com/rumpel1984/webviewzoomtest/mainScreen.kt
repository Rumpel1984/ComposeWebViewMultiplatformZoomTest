package com.rumpel1984.webviewzoomtest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun mainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    val navState by navController.currentBackStackEntryAsState()
                    when (navState?.destination?.route) {
                        "Start" -> Text("Image Transformation")
                        "ex1" -> Text("WebView - Image 1")
                        "ex2" -> Text("WebView - Image 2")
                        "ex3" -> Text("WebView - SVG")
                        "ex4" -> Text("WebView - SVG")
                        else -> Text("Route: "+navState?.destination?.route)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("Start")
                        }
                    ) {
                        Text("Start")
                    }
                    IconButton(
                        onClick = {
                            navController.navigate("ex1")
                        }
                    ) {
                        Text("WV Img-1")
                    }
                    IconButton(
                        onClick = {
                            navController.navigate("ex2")
                        }
                    ) {
                        Text("WV Img-2")
                    }
//                    IconButton(
//                        onClick = {
//                            navController.navigate("ex3")
//                        }
//                    ) {
//                        Text("Ex 3")
//                    }
                    IconButton(
                        onClick = {
                            navController.navigate("ex4")
                        }
                    ) {
                        Text("WV SVG")
                    }
                }
            }
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = "Start",
        ) {
            composable(route = "Start") {
                start()
            }

            composable(route = "ex1") {
                example1()
            }
            composable(route = "ex2") {
                example2()
            }
            composable(route = "ex3") {
                example3()
            }
            composable(route = "ex4") {
                example4()
            }
        }
    }
}