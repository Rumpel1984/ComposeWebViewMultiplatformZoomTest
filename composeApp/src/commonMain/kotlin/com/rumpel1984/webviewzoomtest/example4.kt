package com.rumpel1984.webviewzoomtest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.multiplatform.webview.jsbridge.rememberWebViewJsBridge
import com.multiplatform.webview.util.KLogSeverity
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLFile
import com.rumpel1984.webviewzoomtest.jsbridge.HideDialogJSMessageHandler
import com.rumpel1984.webviewzoomtest.jsbridge.LoadCompletedJSMessageHandler
import com.rumpel1984.webviewzoomtest.jsbridge.ShowDialogJSMessageHandler

@Composable
fun example4() {
    val webViewState = rememberWebViewStateWithHTMLFile("example4.html")
    val webViewNavigator = rememberWebViewNavigator()
    val webViewJsBridge = rememberWebViewJsBridge()

    DisposableEffect(Unit) {
        webViewState.webSettings.apply {
            logSeverity = KLogSeverity.Error
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
        onDispose { }
    }

    LaunchedEffect(webViewJsBridge) {
        webViewJsBridge.register(ShowDialogJSMessageHandler())
        webViewJsBridge.register(HideDialogJSMessageHandler())
        webViewJsBridge.register(LoadCompletedJSMessageHandler())
    }

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState,
        navigator = webViewNavigator,
        webViewJsBridge = webViewJsBridge,
        captureBackPresses = false,
        onCreated = { println("WebView - onCreated") },
        onDispose = { println("WebView - onDispose") },
        factory = null
    )
}