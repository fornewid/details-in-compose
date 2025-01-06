package com.example.compose.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.details.target.TouchTargetScreen5_C

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            DpAsTextUnitScreen()
//            TextStyleDetailsScreen()
//            PreviewInternalsScreen()
//            CustomSnackbarScreen5_E()
            TouchTargetScreen5_C()
        }
    }
}
