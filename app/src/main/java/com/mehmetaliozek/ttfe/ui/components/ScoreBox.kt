package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScoreBox(score: Int, highScore: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.End,
        content = {
            Column {
                Score(text = "Score\n$score")
                Spacer(modifier = Modifier.height(8.dp))
                Score(text = "High Score\n${if (highScore > score) highScore else score}")
            }
        }
    )
}