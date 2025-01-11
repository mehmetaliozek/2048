package com.mehmetaliozek.ttfe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mehmetaliozek.ttfe.ui.theme.Khaki
import com.mehmetaliozek.ttfe.utils.PreferencesManager
import com.mehmetaliozek.ttfe.utils.enums.Direction
import kotlin.math.absoluteValue

@Composable
fun GameBoard(
    modifier: Modifier,
    board: List<List<Int>>,
    score: Int,
    gameOver: Boolean,
    undo: () -> Unit,
    reset: () -> Unit,
    onGameOver: () -> Unit,
    onSwipe: (Direction) -> Unit,
    content: @Composable (Int) -> Unit
) {
    var isDragging by remember { mutableStateOf(false) }
    var preferencesManager: PreferencesManager = PreferencesManager(LocalContext.current)
    var highScore = preferencesManager.getHighScore();
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = { isDragging = false }
                ) { change, dragAmount ->
                    if (isDragging) {
                        val offsetX = dragAmount.x
                        val offsetY = dragAmount.y

                        if (offsetX.absoluteValue > offsetY.absoluteValue) {
                            if (offsetX > 0) {
                                onSwipe(Direction.RIGHT)
                            } else {
                                onSwipe(Direction.LEFT)
                            }
                        } else {
                            if (offsetY > 0) {
                                onSwipe(Direction.DOWN)
                            } else {
                                onSwipe(Direction.UP)
                            }
                        }
                        isDragging = false
                    }
                    change.consume()
                }
            },
        content = {
            ScoreBox(score = score, highScore = highScore)
            Box(modifier = Modifier.align(Alignment.Center),
                content = {
                    Column(
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.End,
                                content = {
                                    GameControlButton(
                                        icon = Rounded.Replay,
                                        onClick = undo
                                    )
                                    GameControlButton(
                                        icon = Rounded.Autorenew,
                                        onClick = reset
                                    )
                                }
                            )
                            Box(
                                content = {
                                    Column(
                                        content = {
                                            for (row in board) {
                                                Row(
                                                    modifier = Modifier.background(Khaki),
                                                    content = {
                                                        for (cell in row) {
                                                            content(cell)
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    )
                                    GameOver(
                                        modifier = Modifier.align(Alignment.Center),
                                        visibility = gameOver,
                                        onClick = {
                                            onGameOver()
                                            preferencesManager.saveHighScore(score)
                                        }
                                    )
                                }
                            )
                        }
                    )
                }
            )

        }
    )
}
