package com.emilianofraile.taskmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
