package com.rumpel1984.webviewzoomtest.jsbridge

import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.web.WebViewNavigator


class ShowDialogJSMessageHandler: IJsMessageHandler {

    override fun methodName(): String {
        return "ShowDialog"
    }

    override fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ) {
        message.params
        println("JSBridge - Show Dialog: "+message.params)
    }

}