package com.example.compose.details.target


import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.unit.DpSize

class CustomViewConfiguration(
    override val minimumTouchTargetSize: DpSize,
    override val longPressTimeoutMillis: Long = 400,
    override val doubleTapTimeoutMillis: Long = 300,
    override val doubleTapMinTimeMillis: Long = 40,
    override val touchSlop: Float = 8f,
) : ViewConfiguration
