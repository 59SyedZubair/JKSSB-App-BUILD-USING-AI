package com.example.data.model

enum class Difficulty(val label: String, val weight: Int) {
    ALL("All Levels", 0),
    EASY("Easy", 1),
    MODERATE("Moderate", 2),
    ADVANCED("Advanced (Exam Level)", 3)
}
