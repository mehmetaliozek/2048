package com.mehmetaliozek.ttfe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehmetaliozek.ttfe.model.GameState
import com.mehmetaliozek.ttfe.utils.enums.Direction
import kotlin.random.Random

const val BOARD_SIZE = 4

class GameViewModel : ViewModel() {
    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = _gameState

    private val _lastGameState = MutableLiveData<GameState>()

    init {
        resetGame()
    }

    private fun updateGameState(newState: GameState) {
        _gameState.value = newState
    }

    fun undoGameState(){
        _gameState.value = _lastGameState.value;
    }

    fun resetGame() {
        val initialBoard = List(BOARD_SIZE) { List(BOARD_SIZE) { 0 } }
        _gameState.value = GameState(initialBoard, 0, false)
        placeRandomNumber()
        placeRandomNumber()
        _lastGameState.value = _gameState.value;
    }

    private fun placeRandomNumber() {
        val currentState = _gameState.value ?: return
        val emptyCells = currentState.board.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, value ->
                if (value == 0) rowIndex to colIndex else null
            }
        }

        if (emptyCells.isNotEmpty()) {
            val (first, second) = emptyCells.random()
            val randomNumber = if (Random.nextBoolean()) 2 else 4
            val newBoard = currentState.board.mapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, cellValue ->
                    if (rowIndex == first && colIndex == second) randomNumber else cellValue
                }
            }
            updateGameState(currentState.copy(board = newBoard))
        }
    }

    fun move(direction: Direction) {
        val currentState = _gameState.value ?: return
        val oldBoard = currentState.board

        val newBoard = when (direction) {
            Direction.LEFT -> moveLeft(currentState.board)
            Direction.RIGHT -> moveRight(currentState.board)
            Direction.UP -> moveUp(currentState.board)
            Direction.DOWN -> moveDown(currentState.board)
        }
        if (newBoard != currentState.board) {
            _lastGameState.value = _gameState.value;
        }

        val newScore = calculateScore(newBoard, oldBoard) + currentState.score
        val gameOver = checkGameOver(newBoard)

        updateGameState(GameState(newBoard, newScore, gameOver))
        if (!gameOver && newBoard != currentState.board) {
            placeRandomNumber()
        }
    }

    private fun moveLeft(board: List<List<Int>>): List<List<Int>> {
        return board.map { merge(it) }
    }

    private fun moveRight(board: List<List<Int>>): List<List<Int>> {
        return board.map { merge(it.reversed()).reversed() }
    }

    private fun moveUp(board: List<List<Int>>): List<List<Int>> {
        val transposed = transpose(board)
        val moved = transposed.map { merge(it) }
        return transpose(moved)
    }

    private fun moveDown(board: List<List<Int>>): List<List<Int>> {
        val transposed = transpose(board)
        val moved = transposed.map { merge(it.reversed()).reversed() }
        return transpose(moved)
    }

    private fun merge(row: List<Int>): List<Int> {
        val nonZero = row.filter { it != 0 }.toMutableList()
        val newRow = mutableListOf<Int>()

        while (nonZero.isNotEmpty()) {
            if (nonZero.size > 1 && nonZero[0] == nonZero[1]) {
                newRow.add(nonZero[0] * 2)
                nonZero.removeAt(0)
                nonZero.removeAt(0)
            } else {
                newRow.add(nonZero.removeAt(0))
            }
        }

        while (newRow.size < BOARD_SIZE) {
            newRow.add(0)
        }

        return newRow
    }

    private fun transpose(board: List<List<Int>>): List<List<Int>> {
        val transposed = MutableList(BOARD_SIZE) { MutableList(BOARD_SIZE) { 0 } }
        for (i in board.indices) {
            for (j in board[i].indices) {
                transposed[j][i] = board[i][j]
            }
        }
        return transposed
    }

    private fun calculateScore(newBoard: List<List<Int>>, oldBoard: List<List<Int>>): Int {
        var score = 0
        for (i in newBoard.indices) {
            for (j in newBoard[i].indices) {
                if (newBoard[i][j] != oldBoard[i][j] && newBoard[i][j] != 0 && oldBoard[i][j] != 0) {
                    score += newBoard[i][j]
                }
            }
        }
        return score
    }

    private fun checkGameOver(board: List<List<Int>>): Boolean {
        if (board.any { row -> row.any { it == 0 } }) return false

        for (i in 0 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                if (i < BOARD_SIZE - 1 && board[i][j] == board[i + 1][j]) return false
                if (j < BOARD_SIZE - 1 && board[i][j] == board[i][j + 1]) return false
            }
        }

        return true
    }
}
