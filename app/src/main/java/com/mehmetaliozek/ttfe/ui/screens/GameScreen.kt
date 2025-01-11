package com.mehmetaliozek.ttfe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mehmetaliozek.ttfe.ui.components.AppBar
import com.mehmetaliozek.ttfe.ui.components.GameBoard
import com.mehmetaliozek.ttfe.ui.components.ResetDialog
import com.mehmetaliozek.ttfe.ui.components.Tile
import com.mehmetaliozek.ttfe.ui.theme.FloralWhite
import com.mehmetaliozek.ttfe.utils.Size
import com.mehmetaliozek.ttfe.viewmodel.BOARD_SIZE
import com.mehmetaliozek.ttfe.viewmodel.GameViewModel

@Composable
fun GameScreen() {
    val gameViewModel: GameViewModel = viewModel()
    val gameState by gameViewModel.gameState.observeAsState()
    val openAlertDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() },
        content = { paddingValues ->
            GameBoard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(FloralWhite),
                board = gameState!!.board,
                score = gameState!!.score,
                gameOver = gameState!!.gameOver,
                onSwipe = { direction ->
                    gameViewModel.move(direction)
                },
                undo = gameViewModel::undoGameState,
                reset = {
                    openAlertDialog.value = true
                },
                onGameOver = gameViewModel::resetGame,
                content = { value ->
                    Tile(
                        modifier = Modifier.size((Size.width() / BOARD_SIZE).dp),
                        value = value
                    )
                }
            )
            if (openAlertDialog.value) {
                ResetDialog(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        gameViewModel.resetGame()
                    },
                    dialogTitle = "Reset Game",
                    dialogText = "Are you sure you want to start the game again?",
                    icon = Icons.Rounded.Autorenew
                )
            }
        }
    )
}