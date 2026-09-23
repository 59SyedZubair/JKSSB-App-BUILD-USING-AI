package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.QuestionBank
import com.example.data.model.Difficulty
import com.example.data.model.LeaderboardEntry
import com.example.data.model.QuestionEntity
import com.example.data.model.StudyTaskEntity
import com.example.data.model.TestResultEntity
import com.example.data.model.UserProfileEntity
import com.example.data.repository.JkssbRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class QuestionFilterType(val label: String) {
    ALL("All MCQs"),
    PYQ_ONLY("PYQs Only 📜"),
    BOOKMARKED("Saved / Starred ⭐"),
    MISTAKES("Weak / Mistakes ❌")
}

class JkssbViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = JkssbRepository(AppDatabase.getInstance(application))

    val allQuestions: StateFlow<List<QuestionEntity>> = repository.allQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedQuestions: StateFlow<List<QuestionEntity>> = repository.bookmarkedQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val testResults: StateFlow<List<TestResultEntity>> = repository.testResults
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val studyTasks: StateFlow<List<StudyTaskEntity>> = repository.studyTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfileEntity())

    // Practice Mode Filter States
    private val _selectedSubjectId = MutableStateFlow("all")
    val selectedSubjectId: StateFlow<String> = _selectedSubjectId.asStateFlow()

    private val _selectedTopic = MutableStateFlow("All Topics")
    val selectedTopic: StateFlow<String> = _selectedTopic.asStateFlow()

    private val _selectedDifficulty = MutableStateFlow(Difficulty.ALL)
    val selectedDifficulty: StateFlow<Difficulty> = _selectedDifficulty.asStateFlow()

    private val _selectedFilterType = MutableStateFlow(QuestionFilterType.ALL)
    val selectedFilterType: StateFlow<QuestionFilterType> = _selectedFilterType.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    data class FilterCriteria(
        val subject: String = "all",
        val topic: String = "All Topics",
        val difficulty: Difficulty = Difficulty.ALL,
        val filterType: QuestionFilterType = QuestionFilterType.ALL,
        val query: String = ""
    )

    private val filterCriteria = combine(
        _selectedSubjectId,
        _selectedTopic,
        _selectedDifficulty,
        _selectedFilterType,
        _searchQuery
    ) { subject, topic, difficulty, filterType, query ->
        FilterCriteria(subject, topic, difficulty, filterType, query)
    }

    // Filtered Question List for Practice
    val filteredQuestions: StateFlow<List<QuestionEntity>> = combine(
        allQuestions,
        filterCriteria
    ) { questions, filter ->
        questions.filter { q ->
            val matchSubject = filter.subject == "all" || q.subjectId == filter.subject
            val matchTopic = filter.topic == "All Topics" || q.topic == filter.topic
            val matchDiff = filter.difficulty == Difficulty.ALL || q.difficulty.equals(filter.difficulty.name, ignoreCase = true)
            val matchType = when (filter.filterType) {
                QuestionFilterType.ALL -> true
                QuestionFilterType.PYQ_ONLY -> q.pyqExamTag.isNotBlank()
                QuestionFilterType.BOOKMARKED -> q.isBookmarked
                QuestionFilterType.MISTAKES -> q.isAnsweredCorrectly == false
            }
            val matchQuery = filter.query.isBlank() ||
                q.questionText.contains(filter.query, ignoreCase = true) ||
                q.writtenExplanation.contains(filter.query, ignoreCase = true) ||
                q.pyqExamTag.contains(filter.query, ignoreCase = true)

            matchSubject && matchTopic && matchDiff && matchType && matchQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Video Solution Dialog State
    private val _activeVideoQuestion = MutableStateFlow<QuestionEntity?>(null)
    val activeVideoQuestion: StateFlow<QuestionEntity?> = _activeVideoQuestion.asStateFlow()

    private val _isVideoPlaying = MutableStateFlow(false)
    val isVideoPlaying: StateFlow<Boolean> = _isVideoPlaying.asStateFlow()

    private val _videoCurrentSecond = MutableStateFlow(0)
    val videoCurrentSecond: StateFlow<Int> = _videoCurrentSecond.asStateFlow()

    private var videoTimerJob: Job? = null

    // Mock Test State
    private val _isMockTestActive = MutableStateFlow(false)
    val isMockTestActive: StateFlow<Boolean> = _isMockTestActive.asStateFlow()

    private val _mockTestQuestions = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val mockTestQuestions: StateFlow<List<QuestionEntity>> = _mockTestQuestions.asStateFlow()

    private val _currentMockIndex = MutableStateFlow(0)
    val currentMockIndex: StateFlow<Int> = _currentMockIndex.asStateFlow()

    private val _mockAnswers = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val mockAnswers: StateFlow<Map<Long, Int>> = _mockAnswers.asStateFlow()

    private val _mockMarkedForReview = MutableStateFlow<Set<Long>>(emptySet())
    val mockMarkedForReview: StateFlow<Set<Long>> = _mockMarkedForReview.asStateFlow()

    private val _mockRemainingSeconds = MutableStateFlow(900) // 15 mins default
    val mockRemainingSeconds: StateFlow<Int> = _mockRemainingSeconds.asStateFlow()

    private val _mockTotalSeconds = MutableStateFlow(900)
    val mockTotalSeconds: StateFlow<Int> = _mockTotalSeconds.asStateFlow()

    private val _latestTestResult = MutableStateFlow<TestResultEntity?>(null)
    val latestTestResult: StateFlow<TestResultEntity?> = _latestTestResult.asStateFlow()

    private var testTimerJob: Job? = null

    // Leaderboard state
    private val _leaderboardPeriod = MutableStateFlow("This Week")
    val leaderboardPeriod: StateFlow<String> = _leaderboardPeriod.asStateFlow()

    private val _leaderboardDistrict = MutableStateFlow("All J&K")
    val leaderboardDistrict: StateFlow<String> = _leaderboardDistrict.asStateFlow()

    init {
        viewModelScope.launch {
            repository.ensureDataPopulated()
        }
    }

    // Filter controls
    fun setSubject(subjectId: String) {
        _selectedSubjectId.value = subjectId
        _selectedTopic.value = "All Topics"
    }

    fun setTopic(topic: String) {
        _selectedTopic.value = topic
    }

    fun setDifficulty(difficulty: Difficulty) {
        _selectedDifficulty.value = difficulty
    }

    fun setFilterType(type: QuestionFilterType) {
        _selectedFilterType.value = type
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // MCQ Practice Actions
    fun submitPracticeAnswer(question: QuestionEntity, selectedOption: Int) {
        val isCorrect = selectedOption == question.correctOption
        viewModelScope.launch {
            repository.recordAttempt(question.id, selectedOption, isCorrect)
        }
    }

    fun toggleBookmark(question: QuestionEntity) {
        viewModelScope.launch {
            repository.toggleBookmark(question.id, question.isBookmarked)
        }
    }

    // Video Solution Dialog Actions
    fun openVideoSolution(question: QuestionEntity) {
        _activeVideoQuestion.value = question
        _videoCurrentSecond.value = 0
        _isVideoPlaying.value = true
        startVideoPlayback(question.videoDurationSec)
    }

    fun closeVideoSolution() {
        stopVideoPlayback()
        _activeVideoQuestion.value = null
        _isVideoPlaying.value = false
        _videoCurrentSecond.value = 0
    }

    fun toggleVideoPlayPause() {
        val playing = !_isVideoPlaying.value
        _isVideoPlaying.value = playing
        if (playing) {
            val total = _activeVideoQuestion.value?.videoDurationSec ?: 100
            startVideoPlayback(total)
        } else {
            stopVideoPlayback()
        }
    }

    fun seekVideo(seconds: Int) {
        _videoCurrentSecond.value = seconds.coerceIn(0, _activeVideoQuestion.value?.videoDurationSec ?: 100)
    }

    private fun startVideoPlayback(totalDuration: Int) {
        videoTimerJob?.cancel()
        videoTimerJob = viewModelScope.launch {
            while (_isVideoPlaying.value && _videoCurrentSecond.value < totalDuration) {
                delay(1000)
                _videoCurrentSecond.value = (_videoCurrentSecond.value + 1).coerceAtMost(totalDuration)
            }
            if (_videoCurrentSecond.value >= totalDuration) {
                _isVideoPlaying.value = false
            }
        }
    }

    private fun stopVideoPlayback() {
        videoTimerJob?.cancel()
        videoTimerJob = null
    }

    // Mock Test Flow
    fun startMockTest(subjectId: String = "all", questionCount: Int = 10, minutes: Int = 15) {
        val pool = allQuestions.value.let { list ->
            if (subjectId == "all") list else list.filter { it.subjectId == subjectId }
        }
        val questions = pool.shuffled().take(questionCount)
        if (questions.isEmpty()) return

        _mockTestQuestions.value = questions
        _currentMockIndex.value = 0
        _mockAnswers.value = emptyMap()
        _mockMarkedForReview.value = emptySet()
        _mockTotalSeconds.value = minutes * 60
        _mockRemainingSeconds.value = minutes * 60
        _latestTestResult.value = null
        _isMockTestActive.value = true

        startTestTimer()
    }

    private fun startTestTimer() {
        testTimerJob?.cancel()
        testTimerJob = viewModelScope.launch {
            while (_mockRemainingSeconds.value > 0 && _isMockTestActive.value) {
                delay(1000)
                _mockRemainingSeconds.value -= 1
            }
            if (_mockRemainingSeconds.value <= 0 && _isMockTestActive.value) {
                submitMockTest()
            }
        }
    }

    fun selectMockAnswer(questionId: Long, optionIndex: Int) {
        val current = _mockAnswers.value.toMutableMap()
        current[questionId] = optionIndex
        _mockAnswers.value = current
    }

    fun toggleMockMarkForReview(questionId: Long) {
        val current = _mockMarkedForReview.value.toMutableSet()
        if (current.contains(questionId)) current.remove(questionId)
        else current.add(questionId)
        _mockMarkedForReview.value = current
    }

    fun setMockIndex(index: Int) {
        if (index in _mockTestQuestions.value.indices) {
            _currentMockIndex.value = index
        }
    }

    fun submitMockTest() {
        testTimerJob?.cancel()
        testTimerJob = null

        val questions = _mockTestQuestions.value
        val answers = _mockAnswers.value
        var correct = 0
        var incorrect = 0

        questions.forEach { q ->
            val userAns = answers[q.id]
            if (userAns != null) {
                if (userAns == q.correctOption) correct++
                else incorrect++
            }
        }
        val unattempted = questions.size - (correct + incorrect)
        val timeTaken = _mockTotalSeconds.value - _mockRemainingSeconds.value

        viewModelScope.launch {
            val result = repository.saveTestResult(
                title = "JKSSB Speed Mock Test",
                totalQuestions = questions.size,
                correctCount = correct,
                incorrectCount = incorrect,
                unattemptedCount = unattempted,
                timeTakenSeconds = timeTaken
            )
            _latestTestResult.value = result
            _isMockTestActive.value = false
        }
    }

    fun dismissTestResult() {
        _latestTestResult.value = null
        _isMockTestActive.value = false
    }

    // Study Plan Actions
    fun toggleStudyTask(task: StudyTaskEntity) {
        viewModelScope.launch {
            repository.toggleStudyTask(task.id, task.isCompleted)
        }
    }

    fun regenerateStudyPlan(exam: String, hours: Int) {
        viewModelScope.launch {
            repository.regenerateStudyPlan(exam, hours, listOf("jk_gk", "english", "maths", "computer"))
        }
    }

    fun updateProfile(name: String, district: String) {
        viewModelScope.launch {
            repository.updateProfile(name, district)
        }
    }

    // Leaderboard Filter
    fun setLeaderboardPeriod(period: String) {
        _leaderboardPeriod.value = period
    }

    fun setLeaderboardDistrict(district: String) {
        _leaderboardDistrict.value = district
    }

    fun getLeaderboardEntries(): List<LeaderboardEntry> {
        val baseList = QuestionBank.sampleLeaderboard
        val currentDistrict = _leaderboardDistrict.value
        return if (currentDistrict == "All J&K") baseList
        else baseList.filter { it.district.equals(currentDistrict, ignoreCase = true) || it.isCurrentUser }
    }
}
