package com.mehmetaliozek.ttfe.model

data class GameState(
    val board: List<List<Int>>,
    val score: Int,
    val gameOver: Boolean
)
