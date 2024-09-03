package com.rumpel1984.webviewzoomtest

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.multiplatform.webview.jsbridge.rememberWebViewJsBridge
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLFile
import com.rumpel1984.webviewzoomtest.jsbridge.HideDialogJSMessageHandler
import com.rumpel1984.webviewzoomtest.jsbridge.LoadCompletedJSMessageHandler
import com.rumpel1984.webviewzoomtest.jsbridge.ShowDialogJSMessageHandler
import composewebviewmultiplatformzoomtest.composeapp.generated.resources.Res
import composewebviewmultiplatformzoomtest.composeapp.generated.resources.picture
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        example1()
    }
}

@Composable
fun navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Start",
    ) {
        composable(route = "Start") {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            val navState by navController.currentBackStackEntryAsState()
                            if (navState?.destination?.route == "Start")
                                Text("Start")
                            else if (navState?.destination?.route == "ex1")
                                Text("Ex 1: <img> JPEG")
                            else if (navState?.destination?.route == "ex2")
                                Text("Ex 2: <img> SVG")
                            else if (navState?.destination?.route == "ex3")
                                Text("Ex 3: <object> SVG")
                            else if (navState?.destination?.route == "ex4")
                                Text("Ex 4: JS XMLHttpRequest SVG")
                            else
                                Text("Route: "+navState?.destination?.route)
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar(

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(
                                onClick = {
                                    navController.navigate("ex1")
                                }
                            ) {
                                Text("Exmpl 1")
                            }
                            IconButton(
                                onClick = {
                                    navController.navigate("ex2")
                                }
                            ) {
                                Text("Exmpl 2")
                            }
                            IconButton(
                                onClick = {
                                    navController.navigate("ex3")
                                }
                            ) {
                                Text("Exmpl 3")
                            }
                            IconButton(
                                onClick = {
                                    navController.navigate("ex4")
                                }
                            ) {
                                Text("Exmpl 4")
                            }
                        }
                    }
                }
            ) { paddingValues ->
                startScreen()
            }
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

@Composable
fun startScreen() {
    Column() {
        Text("This is just the Start!")
        var scale by remember {
            mutableStateOf(1f)
        }
        var offset by remember {
            mutableStateOf(Offset.Zero)
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1280f / 959f)
        ) {
            val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                println("Transform - zoomChange: $zoomChange")
                scale = (scale * zoomChange).coerceIn(1f, 5f)

                val extraWidth = (scale - 1) * constraints.maxWidth
                val extraHeight = (scale - 1) * constraints.maxHeight

                val maxX = extraWidth / 2
                val maxY = extraHeight / 2

                offset = Offset(
                    x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
                )
            }
            Image(
                painter = painterResource(Res.drawable.picture),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
                    .transformable(state)
            )
        }

    }
}

@Composable
fun example1() {
    val webViewState1 = rememberWebViewStateWithHTMLFile("example1.html")
    val webViewNavigator1 = rememberWebViewNavigator()

    webViewState1.webSettings.apply {
        isJavaScriptEnabled = true
        supportZoom = true
        zoomLevel = 1.0
        allowFileAccessFromFileURLs = true
        allowUniversalAccessFromFileURLs = true
        androidWebSettings.apply {
            useWideViewPort = true
            safeBrowsingEnabled = true
            allowFileAccess = true
        }
    }

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState1,
        navigator = webViewNavigator1,
        captureBackPresses = false
    )
}

@Composable
fun example2() {
    val webViewState2 = rememberWebViewStateWithHTMLFile("example2.html")
    val webViewNavigator2 = rememberWebViewNavigator()

    webViewState2.webSettings.apply {
        isJavaScriptEnabled = true
        supportZoom = true
        zoomLevel = 1.0
        allowFileAccessFromFileURLs = true
        allowUniversalAccessFromFileURLs = true
        androidWebSettings.apply {
            useWideViewPort = true
            safeBrowsingEnabled = true
            allowFileAccess = true
        }
    }

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState2,
        navigator = webViewNavigator2,
        captureBackPresses = false
    )
}

@Composable
fun example3() {
    val webViewState3 = rememberWebViewStateWithHTMLFile("example3.html")
    val webViewNavigator3 = rememberWebViewNavigator()
    val webViewJsBridge3 = rememberWebViewJsBridge()

    webViewState3.webSettings.apply {
        isJavaScriptEnabled = true
        supportZoom = true
        zoomLevel = 1.0
        allowFileAccessFromFileURLs = true
        allowUniversalAccessFromFileURLs = true
        androidWebSettings.apply {
            useWideViewPort = true
            safeBrowsingEnabled = true
            allowFileAccess = true
        }
    }

    webViewJsBridge3.register(ShowDialogJSMessageHandler())
    webViewJsBridge3.register(HideDialogJSMessageHandler())
    webViewJsBridge3.register(LoadCompletedJSMessageHandler())

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState3,
        navigator = webViewNavigator3,
        webViewJsBridge = webViewJsBridge3,
        captureBackPresses = false
    )
}

@Composable
fun example4() {
    val webViewState4 = rememberWebViewStateWithHTMLFile("example4.html")
    val webViewNavigator4 = rememberWebViewNavigator()
    val webViewJsBridge4 = rememberWebViewJsBridge()

    webViewState4.webSettings.apply {
        isJavaScriptEnabled = true
        supportZoom = true
        zoomLevel = 1.0
        allowFileAccessFromFileURLs = true
        allowUniversalAccessFromFileURLs = true
        androidWebSettings.apply {
            useWideViewPort = true
            safeBrowsingEnabled = true
            allowFileAccess = true
        }
    }

    webViewJsBridge4.register(ShowDialogJSMessageHandler())
    webViewJsBridge4.register(HideDialogJSMessageHandler())
    webViewJsBridge4.register(LoadCompletedJSMessageHandler())

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState4,
        navigator = webViewNavigator4,
        webViewJsBridge = webViewJsBridge4,
        captureBackPresses = false
    )
}