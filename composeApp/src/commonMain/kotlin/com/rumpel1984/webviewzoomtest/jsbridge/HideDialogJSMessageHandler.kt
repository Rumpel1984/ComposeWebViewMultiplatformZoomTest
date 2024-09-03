package com.rumpel1984.webviewzoomtest.jsbridge

import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.web.WebViewNavigator

class HideDialogJSMessageHandler: IJsMessageHandler {

    override fun methodName(): String {
        return "HideDialog"
    }

    override fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ) {
        println("JSBridge - Hide Dialog: "+message.params)
    }

}