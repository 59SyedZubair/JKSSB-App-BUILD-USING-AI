package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE subjectId = :subjectId ORDER BY id ASC")
    fun getQuestionsBySubject(subjectId: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE subjectId = :subjectId AND topic = :topic ORDER BY id ASC")
    fun getQuestionsBySubjectAndTopic(subjectId: String, topic: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE isBookmarked = 1 ORDER BY id DESC")
    fun getBookmarkedQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE isAnsweredCorrectly = 0 ORDER BY id DESC")
    fun getIncorrectQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE pyqExamTag != '' ORDER BY id ASC")
    fun getPyqQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Long): QuestionEntity?

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)

    @Query("UPDATE questions SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmark(id: Long, isBookmarked: Boolean)

    @Query("UPDATE questions SET userSelectedOption = :selectedOption, isAnsweredCorrectly = :isCorrect, attemptCount = attemptCount + 1 WHERE id = :id")
    suspend fun recordAttempt(id: Long, selectedOption: Int, isCorrect: Boolean)

    @Query("UPDATE questions SET userSelectedOption = NULL, isAnsweredCorrectly = NULL")
    suspend fun resetAllAttempts()
}
