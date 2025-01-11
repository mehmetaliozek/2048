package com.mehmetaliozek.ttfe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mehmetaliozek.ttfe.ui.screens.GameScreen
import com.mehmetaliozek.ttfe.ui.theme.TtfeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TtfeTheme {
                GameScreen()
            }
        }
    }
}
