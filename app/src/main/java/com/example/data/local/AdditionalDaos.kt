package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.StudyTaskEntity
import com.example.data.model.TestResultEntity
import com.example.data.model.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultDao {
    @Query("SELECT * FROM test_results ORDER BY timestamp DESC")
    fun getAllResults(): Flow<List<TestResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TestResultEntity): Long

    @Query("DELETE FROM test_results")
    suspend fun clearAll()
}

@Dao
interface StudyTaskDao {
    @Query("SELECT * FROM study_tasks ORDER BY isCompleted ASC, id ASC")
    fun getAllTasks(): Flow<List<StudyTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<StudyTaskEntity>)

    @Update
    suspend fun updateTask(task: StudyTaskEntity)

    @Query("UPDATE study_tasks SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun setTaskCompleted(id: Long, isCompleted: Boolean)

    @Query("DELETE FROM study_tasks")
    suspend fun clearTasks()
}

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(profile: UserProfileEntity)

    @Query("UPDATE user_profile SET totalXp = totalXp + :xp, totalQuestionsSolved = totalQuestionsSolved + 1, totalCorrect = totalCorrect + (CASE WHEN :isCorrect THEN 1 ELSE 0 END)")
    suspend fun addMcqStats(xp: Int, isCorrect: Boolean)

    @Query("UPDATE user_profile SET targetExam = :targetExam, dailyTargetHours = :hours")
    suspend fun updateExamPreferences(targetExam: String, hours: Int)

    @Query("UPDATE user_profile SET district = :district, name = :name")
    suspend fun updateProfileInfo(name: String, district: String)
}
