package com.app.skyscanner

import androidx.compose.ui.window.ComposeUIViewController
import com.app.skyscanner.app.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}