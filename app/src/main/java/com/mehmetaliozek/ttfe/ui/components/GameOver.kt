package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetaliozek.ttfe.ui.theme.Beaver
import com.mehmetaliozek.ttfe.ui.theme.Linen
import com.mehmetaliozek.ttfe.ui.theme.nunitoFontFamily
import com.mehmetaliozek.ttfe.utils.Size

@Composable
fun GameOver(modifier: Modifier, visibility: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visibility,
        enter = fadeIn(animationSpec = TweenSpec(1000)),
        exit = fadeOut(),
        content = {
            Box(
                modifier = modifier
                    .size(Size.width().dp)
                    .background(Linen.copy(alpha = 0.85f))
                    .clickable(onClick = onClick),
                content = {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Game Over",
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.W900,
                        fontSize = 36.sp,
                        color = Beaver
                    )
                }
            )
        }
    )
}