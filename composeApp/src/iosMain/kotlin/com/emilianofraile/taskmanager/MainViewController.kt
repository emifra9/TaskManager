package com.emilianofraile.taskmanager

import androidx.compose.ui.window.ComposeUIViewController
import com.emilianofraile.taskmanager.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}