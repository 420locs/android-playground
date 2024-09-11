package com.example.trashArchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sample.MyClass
import com.example.trashArchitecture.ui.theme.TrashArchitectureTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val test by inject<MyClass>()
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // Handle the exception here
        Log.e("CoroutineExceptionHandler", "Caught exception: $exception")
        // You can add more specific handling based on the exception type
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrashArchitectureTheme {
                val coroutineScope = rememberCoroutineScope()
                LaunchedEffect(key1 = Unit) {
                    coroutineScope.launch(coroutineExceptionHandler) {
                        Log.d("Ninh", "onCreate: ${test()}")
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Ninh vjp pro",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrashArchitectureTheme {
        Greeting("Android")
    }
}