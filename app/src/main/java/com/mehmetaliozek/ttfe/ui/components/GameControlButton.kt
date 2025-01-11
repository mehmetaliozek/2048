package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.mehmetaliozek.ttfe.ui.theme.FloralWhite
import com.mehmetaliozek.ttfe.ui.theme.Khaki

@Composable
fun GameControlButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Khaki,
            contentColor = FloralWhite
        ),
        content =
        {
            Icon(icon, contentDescription = "Repeat")
        }
    )
}