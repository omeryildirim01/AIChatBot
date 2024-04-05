package com.yildirimomer.aichatbot.presentation


sealed class AppScreen(root: String) {
    data object HomesScreen: AppScreen(root = "home_screen")
}


