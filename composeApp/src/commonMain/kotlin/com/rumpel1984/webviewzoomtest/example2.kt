package com.rumpel1984.webviewzoomtest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.multiplatform.webview.util.KLogSeverity
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLFile

@Composable
fun example2() {
    val webViewState = rememberWebViewStateWithHTMLFile("example1.html")
    val webViewNavigator = rememberWebViewNavigator()

    DisposableEffect(Unit) {
        webViewState.webSettings.apply {
            logSeverity = KLogSeverity.Error
            isJavaScriptEnabled = true
            supportZoom = true
            zoomLevel = 0.4
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        WebView(
            modifier = Modifier.fillMaxSize(),
            state = webViewState,
            navigator = webViewNavigator,
            captureBackPresses = false,
            onCreated = { println("WebView - onCreated") },
            onDispose = { println("WebView - onDispose") },
            factory = null
        )
    }
}