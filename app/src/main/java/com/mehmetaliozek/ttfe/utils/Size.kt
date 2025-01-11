package com.mehmetaliozek.ttfe.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

class Size {
    companion object {
        @Composable
        fun height(): Int {
            val configuration = LocalConfiguration.current
            return configuration.screenHeightDp
        }

        @Composable
        fun width(): Int {
            val configuration = LocalConfiguration.current
            return configuration.screenWidthDp
        }
    }
}