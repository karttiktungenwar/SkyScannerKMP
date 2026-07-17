package com.app.skyscanner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform