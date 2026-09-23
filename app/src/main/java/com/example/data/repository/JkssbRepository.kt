package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.QuestionBank
import com.example.data.model.QuestionEntity
import com.example.data.model.StudyTaskEntity
import com.example.data.model.TestResultEntity
import com.example.data.model.UserProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class JkssbRepository(private val database: AppDatabase) {
    private val questionDao = database.questionDao()
    private val testResultDao = database.testResultDao()
    private val studyTaskDao = database.studyTaskDao()
    private val userProfileDao = database.userProfileDao()

    val allQuestions: Flow<List<QuestionEntity>> = questionDao.getAllQuestions()
    val bookmarkedQuestions: Flow<List<QuestionEntity>> = questionDao.getBookmarkedQuestions()
    val incorrectQuestions: Flow<List<QuestionEntity>> = questionDao.getIncorrectQuestions()
    val testResults: Flow<List<TestResultEntity>> = testResultDao.getAllResults()
    val studyTasks: Flow<List<StudyTaskEntity>> = studyTaskDao.getAllTasks()
    val userProfile: Flow<UserProfileEntity?> = userProfileDao.getUserProfile()

    suspend fun ensureDataPopulated() {
        val count = questionDao.getQuestionCount()
        if (count == 0) {
            questionDao.insertAll(QuestionBank.initialQuestions)
            studyTaskDao.insertTasks(QuestionBank.defaultStudyTasks)
            userProfileDao.insertOrUpdate(UserProfileEntity())
        }
    }

    fun getQuestionsBySubject(subjectId: String): Flow<List<QuestionEntity>> {
        return if (subjectId == "all") questionDao.getAllQuestions()
        else questionDao.getQuestionsBySubject(subjectId)
    }

    suspend fun toggleBookmark(questionId: Long, currentStatus: Boolean) {
        questionDao.updateBookmark(questionId, !currentStatus)
    }

    suspend fun recordAttempt(questionId: Long, selectedOption: Int, isCorrect: Boolean) {
        questionDao.recordAttempt(questionId, selectedOption, isCorrect)
        val xpGain = if (isCorrect) 10 else 2
        userProfileDao.addMcqStats(xp = xpGain, isCorrect = isCorrect)
    }

    suspend fun saveTestResult(
        title: String,
        totalQuestions: Int,
        correctCount: Int,
        incorrectCount: Int,
        unattemptedCount: Int,
        timeTakenSeconds: Int
    ): TestResultEntity {
        // JKSSB Marking Scheme: +1 per correct, -0.25 per wrong, 0 for unattempted
        val score = (correctCount * 1.0f) - (incorrectCount * 0.25f)
        val maxScore = totalQuestions * 1.0f
        val accuracy = if (correctCount + incorrectCount > 0) {
            (correctCount.toFloat() / (correctCount + incorrectCount).toFloat()) * 100f
        } else 0f

        val result = TestResultEntity(
            title = title,
            totalQuestions = totalQuestions,
            correctCount = correctCount,
            incorrectCount = incorrectCount,
            unattemptedCount = unattemptedCount,
            score = score.coerceAtLeast(0f),
            maxScore = maxScore,
            accuracy = accuracy,
            timeTakenSeconds = timeTakenSeconds
        )

        testResultDao.insertResult(result)

        // Award bonus XP for mock tests
        val mockXp = (score * 15).toInt().coerceAtLeast(20)
        userProfileDao.addMcqStats(xp = mockXp, isCorrect = true)

        return result
    }

    suspend fun toggleStudyTask(taskId: Long, isCompleted: Boolean) {
        studyTaskDao.setTaskCompleted(taskId, !isCompleted)
        if (!isCompleted) {
            userProfileDao.addMcqStats(xp = 25, isCorrect = true)
        }
    }

    suspend fun regenerateStudyPlan(targetExam: String, hours: Int, subjects: List<String>) {
        studyTaskDao.clearTasks()
        val newTasks = mutableListOf<StudyTaskEntity>()

        newTasks.add(
            StudyTaskEntity(
                title = "Target Focus: $targetExam",
                description = "Complete 25 high-yield MCQs covering syllabus core",
                subjectId = "jk_gk",
                targetType = "MCQ_COUNT",
                targetCount = 25,
                currentProgress = 0,
                isCompleted = false,
                dateString = "Today"
            )
        )

        newTasks.add(
            StudyTaskEntity(
                title = "Video Solution Concept Review",
                description = "Watch 2 in-depth question walkthroughs to master elimination tricks",
                subjectId = "maths",
                targetType = "VIDEO_WATCH",
                targetCount = 2,
                currentProgress = 0,
                isCompleted = false,
                dateString = "Today"
            )
        )

        newTasks.add(
            StudyTaskEntity(
                title = "Speed Test & Weak Areas Drill",
                description = "Attempt 15 questions timed mock with negative marking",
                subjectId = "all",
                targetType = "MOCK_TEST",
                targetCount = 1,
                currentProgress = 0,
                isCompleted = false,
                dateString = "Today"
            )
        )

        if (hours >= 3) {
            newTasks.add(
                StudyTaskEntity(
                    title = "Rapid Revision: English & Computer Shortcuts",
                    description = "Revise idioms, prepositions and MS Office functional shortcuts",
                    subjectId = "computer",
                    targetType = "REVISION",
                    targetCount = 1,
                    currentProgress = 0,
                    isCompleted = false,
                    dateString = "Today"
                )
            )
        }

        studyTaskDao.insertTasks(newTasks)
        userProfileDao.updateExamPreferences(targetExam, hours)
    }

    suspend fun updateProfile(name: String, district: String) {
        userProfileDao.updateProfileInfo(name, district)
    }

    suspend fun resetAllProgress() {
        questionDao.resetAllAttempts()
        testResultDao.clearAll()
    }
}
