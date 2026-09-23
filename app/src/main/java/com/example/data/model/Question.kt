package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class VideoSolution(
    val title: String,
    val instructorName: String,
    val instructorDesignation: String,
    val durationSeconds: Int,
    val keyConcept: String,
    val shortcutTip: String,
    val timestamps: List<VideoTimestamp> = emptyList()
)

data class VideoTimestamp(
    val second: Int,
    val label: String
)

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val subjectId: String,
    val topic: String,
    val difficulty: String, // "EASY", "MODERATE", "ADVANCED"
    val questionText: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOption: Int, // 0 for A, 1 for B, 2 for C, 3 for D
    val writtenExplanation: String,
    val pyqExamTag: String, // e.g. "JKSSB Panchayat Secretary 2023"
    val videoTitle: String,
    val videoInstructor: String,
    val videoDurationSec: Int,
    val videoKeyConcept: String,
    val videoShortcut: String,
    val isBookmarked: Boolean = false,
    val userSelectedOption: Int? = null,
    val isAnsweredCorrectly: Boolean? = null,
    val attemptCount: Int = 0
) {
    fun getOptions(): List<String> = listOf(optionA, optionB, optionC, optionD)

    fun toVideoSolution(): VideoSolution = VideoSolution(
        title = videoTitle,
        instructorName = videoInstructor,
        instructorDesignation = "JKSSB Senior Faculty",
        durationSeconds = videoDurationSec,
        keyConcept = videoKeyConcept,
        shortcutTip = videoShortcut,
        timestamps = listOf(
            VideoTimestamp(0, "Question Reading & Trap Identification"),
            VideoTimestamp(videoDurationSec / 4, "Core Concept & Background Theory"),
            VideoTimestamp(videoDurationSec / 2, "Detailed Step-by-Step Resolution"),
            VideoTimestamp((videoDurationSec * 3) / 4, "Smart Elimination & Exam Shortcut")
        )
    )
}
