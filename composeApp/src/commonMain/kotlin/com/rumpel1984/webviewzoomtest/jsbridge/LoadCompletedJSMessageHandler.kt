package com.rumpel1984.webviewzoomtest.jsbridge

import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.web.WebViewNavigator

class LoadCompletedJSMessageHandler: IJsMessageHandler {

    override fun methodName(): String {
        return "LoadCompleted"
    }

    override fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ) {
        println("JSBridge - Load Completed")
    }

}