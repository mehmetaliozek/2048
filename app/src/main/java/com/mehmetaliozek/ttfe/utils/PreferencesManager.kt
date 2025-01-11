package com.mehmetaliozek.ttfe.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_HIGH_SCORE = "high_score"
    }

    fun saveHighScore(score: Int) {
        sharedPreferences.edit().putInt(KEY_HIGH_SCORE, score).apply()
    }

    fun getHighScore(): Int {
        return sharedPreferences.getInt(KEY_HIGH_SCORE, 0)
    }
}