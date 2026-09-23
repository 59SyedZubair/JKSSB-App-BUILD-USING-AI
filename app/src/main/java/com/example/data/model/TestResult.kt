package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_results")
data class TestResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val incorrectCount: Int,
    val unattemptedCount: Int,
    val score: Float, // Correct * 1.0 - Incorrect * 0.25
    val maxScore: Float,
    val accuracy: Float,
    val timeTakenSeconds: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "study_tasks")
data class StudyTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val subjectId: String,
    val targetType: String, // "MCQ_COUNT", "VIDEO_WATCH", "MOCK_TEST", "REVISION"
    val targetCount: Int = 1,
    val currentProgress: Int = 0,
    val isCompleted: Boolean = false,
    val dateString: String // e.g. "2026-09-23"
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val name: String = "Aspirant",
    val district: String = "Srinagar",
    val targetExam: String = "JKSSB Panchayat Secretary (VLW)",
    val dailyTargetHours: Int = 3,
    val examDateMillis: Long = System.currentTimeMillis() + 45L * 24 * 3600 * 1000, // 45 days from now
    val streakDays: Int = 5,
    val totalXp: Int = 1250,
    val totalQuestionsSolved: Int = 42,
    val totalCorrect: Int = 35,
    val studyMinutes: Int = 180,
    val lastActiveDate: String = ""
)

data class LeaderboardEntry(
    val rank: Int,
    val name: String,
    val district: String,
    val xp: Int,
    val accuracy: Int,
    val testsCount: Int,
    val badge: String,
    val isCurrentUser: Boolean = false
)
