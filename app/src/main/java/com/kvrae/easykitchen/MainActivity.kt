package com.kvrae.easykitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kvrae.easykitchen.ui.screens.MainScreen
import com.kvrae.easykitchen.ui.theme.EasyKitchenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyKitchenTheme {
                MainScreen()
            }
        }
    }
}