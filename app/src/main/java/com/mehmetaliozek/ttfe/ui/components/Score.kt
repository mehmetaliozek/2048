package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mehmetaliozek.ttfe.ui.theme.DimGray
import com.mehmetaliozek.ttfe.ui.theme.FloralWhite
import com.mehmetaliozek.ttfe.ui.theme.Khaki
import com.mehmetaliozek.ttfe.ui.theme.Linen
import com.mehmetaliozek.ttfe.ui.theme.nunitoFontFamily
import com.mehmetaliozek.ttfe.utils.Size

@Composable
fun Score(text: String) {
    Box(
        modifier = Modifier
            .size(width = (Size.width() / 4).dp, height = 50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Khaki),
        content = {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                fontFamily = nunitoFontFamily,
                textAlign = TextAlign.Center,
                color = FloralWhite
            )
        }
    )
}