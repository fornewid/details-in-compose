package com.example.compose.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.details.text.style.TextStyleDetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DpAsTextUnitScreen()
            TextStyleDetailsScreen()
        }
    }
}
