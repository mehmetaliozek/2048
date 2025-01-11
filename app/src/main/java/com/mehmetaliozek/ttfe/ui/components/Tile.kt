package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetaliozek.ttfe.ui.theme.Beaver
import com.mehmetaliozek.ttfe.ui.theme.Linen
import com.mehmetaliozek.ttfe.ui.theme.nunitoFontFamily
import com.mehmetaliozek.ttfe.utils.Size
import com.mehmetaliozek.ttfe.utils.enums.TileColor
import com.mehmetaliozek.ttfe.viewmodel.BOARD_SIZE

@Composable
fun Tile(modifier: Modifier, value: Int) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(((Size.width() / BOARD_SIZE) - 7.5).dp)
                .clip(RoundedCornerShape(8.dp))
                .background(TileColor.fromValue(value).color),
            content = {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "${if (value != 0) value else ""}",
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.W900,
                    fontSize = when (value.toString().length) {
                        1, 2 -> 36.sp
                        3 -> 33.sp
                        4 -> 30.sp
                        5 -> 27.sp
                        6 -> 24.sp
                        else -> 36.sp
                    },
                    color = if (value <= 4) Beaver else Linen
                )
            }
        )
    }
}