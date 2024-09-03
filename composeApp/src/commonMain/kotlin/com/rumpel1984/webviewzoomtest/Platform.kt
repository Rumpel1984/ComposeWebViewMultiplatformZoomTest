package com.rumpel1984.webviewzoomtest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform