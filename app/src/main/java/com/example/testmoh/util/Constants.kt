package com.example.testmoh.util

import androidx.compose.ui.unit.dp

/**
 * Defines global constants for the application, especially related to UI dimensions.
 * These DP values are scaled to allow detailed designs to be legible within a physical
 * 58mm x 80mm screen area on a high-DPI POS device.
 */
object Constants {
    // Conceptual POS device screen dimensions in dp (based on 58mm x 80mm on an 8-inch device)
    val POS_SCREEN_WIDTH = 230.dp
    val POS_SCREEN_HEIGHT = 315.dp

    // Consistent padding for left-aligned content, adjusted for the new larger dp scale
    val CONTENT_START_PADDING = 16.dp // Adjusted from 24dp for better fit on 230dp width
}
