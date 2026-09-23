package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.QuestionEntity
import com.example.data.model.StudyTaskEntity
import com.example.data.model.TestResultEntity
import com.example.data.model.UserProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        QuestionEntity::class,
        TestResultEntity::class,
        StudyTaskEntity::class,
        UserProfileEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun testResultDao(): TestResultDao
    abstract fun studyTaskDao(): StudyTaskDao
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "jkssb_prep_database.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Pre-populate with initial authentic JKSSB MCQs and study tasks
                            CoroutineScope(Dispatchers.IO).launch {
                                val database = getInstance(context)
                                database.questionDao().insertAll(QuestionBank.initialQuestions)
                                database.studyTaskDao().insertTasks(QuestionBank.defaultStudyTasks)
                                database.userProfileDao().insertOrUpdate(UserProfileEntity())
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
