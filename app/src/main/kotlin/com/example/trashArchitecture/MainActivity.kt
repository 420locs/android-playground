package com.example.trashArchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sample.presentation.main.SampleScreen
import com.example.trashArchitecture.ui.theme.TrashArchitectureTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrashArchitectureTheme {
                SampleScreen()
            }
        }
    }
}
