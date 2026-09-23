package com.example.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.UserProfileEntity
import com.example.ui.components.VideoSolutionDialog
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.McqPracticeScreen
import com.example.ui.screens.MockTestScreen
import com.example.ui.screens.StudyPlanScreen
import com.example.ui.viewmodel.JkssbViewModel

enum class NavigationItem(val route: String, val title: String, val icon: ImageVector) {
    DASHBOARD("dashboard", "Dashboard", Icons.Default.Dashboard),
    PRACTICE("practice", "Practice", Icons.Default.MenuBook),
    MOCK_TEST("mock_test", "Mock Test", Icons.Default.Quiz),
    STUDY_PLAN("study_plan", "Study Plan", Icons.Default.FitnessCenter),
    LEADERBOARD("leaderboard", "Ranks", Icons.Default.EmojiEvents)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(viewModel: JkssbViewModel) {
    var currentItem by remember { mutableStateOf(NavigationItem.DASHBOARD) }
    var showProfileDialog by remember { mutableStateOf(false) }

    val allQuestions by viewModel.allQuestions.collectAsStateWithLifecycle()
    val filteredQuestions by viewModel.filteredQuestions.collectAsStateWithLifecycle()
    val testResults by viewModel.testResults.collectAsStateWithLifecycle()
    val studyTasks by viewModel.studyTasks.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()

    val selectedSubjectId by viewModel.selectedSubjectId.collectAsStateWithLifecycle()
    val selectedTopic by viewModel.selectedTopic.collectAsStateWithLifecycle()
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsStateWithLifecycle()
    val selectedFilterType by viewModel.selectedFilterType.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    val activeVideoQuestion by viewModel.activeVideoQuestion.collectAsStateWithLifecycle()
    val isVideoPlaying by viewModel.isVideoPlaying.collectAsStateWithLifecycle()
    val videoCurrentSecond by viewModel.videoCurrentSecond.collectAsStateWithLifecycle()

    val isMockActive by viewModel.isMockTestActive.collectAsStateWithLifecycle()
    val mockQuestions by viewModel.mockTestQuestions.collectAsStateWithLifecycle()
    val currentMockIndex by viewModel.currentMockIndex.collectAsStateWithLifecycle()
    val mockAnswers by viewModel.mockAnswers.collectAsStateWithLifecycle()
    val mockMarkedReview by viewModel.mockMarkedForReview.collectAsStateWithLifecycle()
    val mockRemainingSec by viewModel.mockRemainingSeconds.collectAsStateWithLifecycle()
    val latestTestResult by viewModel.latestTestResult.collectAsStateWithLifecycle()

    val leaderboardPeriod by viewModel.leaderboardPeriod.collectAsStateWithLifecycle()
    val leaderboardDistrict by viewModel.leaderboardDistrict.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "JKSSB Prep",
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "🏔️", fontSize = 16.sp)
                    }
                },
                actions = {
                    // Streak indicator
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFEF3C7)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = Color(0xFFD97706),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${userProfile?.streakDays ?: 1}",
                                color = Color(0xFF92400E),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // XP Badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = "${userProfile?.totalXp ?: 1000} XP",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Profile Editor
                    IconButton(
                        onClick = { showProfileDialog = true },
                        modifier = Modifier.testTag("open_profile_dialog_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.testTag("bottom_nav_bar")
            ) {
                NavigationItem.values().forEach { item ->
                    NavigationBarItem(
                        selected = currentItem == item,
                        onClick = { currentItem = item },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title, fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_item_${item.route}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentItem) {
                NavigationItem.DASHBOARD -> {
                    DashboardScreen(
                        userProfile = userProfile,
                        allQuestions = allQuestions,
                        testResults = testResults,
                        onNavigateToPractice = { subjectId ->
                            viewModel.setSubject(subjectId)
                            currentItem = NavigationItem.PRACTICE
                        },
                        onNavigateToMockTest = {
                            currentItem = NavigationItem.MOCK_TEST
                        },
                        onNavigateToStudyPlan = {
                            currentItem = NavigationItem.STUDY_PLAN
                        },
                        onNavigateToLeaderboard = {
                            currentItem = NavigationItem.LEADERBOARD
                        }
                    )
                }
                NavigationItem.PRACTICE -> {
                    McqPracticeScreen(
                        questions = filteredQuestions,
                        selectedSubjectId = selectedSubjectId,
                        selectedTopic = selectedTopic,
                        selectedDifficulty = selectedDifficulty,
                        selectedFilterType = selectedFilterType,
                        searchQuery = searchQuery,
                        onSelectSubject = { viewModel.setSubject(it) },
                        onSelectTopic = { viewModel.setTopic(it) },
                        onSelectDifficulty = { viewModel.setDifficulty(it) },
                        onSelectFilterType = { viewModel.setFilterType(it) },
                        onSearchQueryChange = { viewModel.setSearchQuery(it) },
                        onOptionSelected = { q, opt -> viewModel.submitPracticeAnswer(q, opt) },
                        onToggleBookmark = { q -> viewModel.toggleBookmark(q) },
                        onOpenVideoSolution = { q -> viewModel.openVideoSolution(q) }
                    )
                }
                NavigationItem.MOCK_TEST -> {
                    MockTestScreen(
                        isActive = isMockActive,
                        questions = mockQuestions,
                        currentIndex = currentMockIndex,
                        answers = mockAnswers,
                        markedForReview = mockMarkedReview,
                        remainingSeconds = mockRemainingSec,
                        latestResult = latestTestResult,
                        onStartTest = { sub, count, mins ->
                            viewModel.startMockTest(sub, count, mins)
                        },
                        onSelectAnswer = { qId, opt -> viewModel.selectMockAnswer(qId, opt) },
                        onToggleMarkReview = { qId -> viewModel.toggleMockMarkForReview(qId) },
                        onSelectIndex = { idx -> viewModel.setMockIndex(idx) },
                        onSubmitTest = { viewModel.submitMockTest() },
                        onDismissResult = { viewModel.dismissTestResult() }
                    )
                }
                NavigationItem.STUDY_PLAN -> {
                    StudyPlanScreen(
                        tasks = studyTasks,
                        userProfile = userProfile,
                        onToggleTask = { viewModel.toggleStudyTask(it) },
                        onRegeneratePlan = { exam, hours -> viewModel.regenerateStudyPlan(exam, hours) }
                    )
                }
                NavigationItem.LEADERBOARD -> {
                    LeaderboardScreen(
                        entries = viewModel.getLeaderboardEntries(),
                        selectedPeriod = leaderboardPeriod,
                        selectedDistrict = leaderboardDistrict,
                        onSelectPeriod = { viewModel.setLeaderboardPeriod(it) },
                        onSelectDistrict = { viewModel.setLeaderboardDistrict(it) }
                    )
                }
            }
        }
    }

    // Video Solution Dialog Modal
    if (activeVideoQuestion != null) {
        VideoSolutionDialog(
            question = activeVideoQuestion!!,
            isPlaying = isVideoPlaying,
            currentSecond = videoCurrentSecond,
            onTogglePlay = { viewModel.toggleVideoPlayPause() },
            onSeek = { viewModel.seekVideo(it) },
            onDismiss = { viewModel.closeVideoSolution() }
        )
    }

    // Profile Dialog Modal
    if (showProfileDialog) {
        val currentProfile = userProfile ?: UserProfileEntity()
        var nameInput by remember { mutableStateOf(currentProfile.name) }
        var districtInput by remember { mutableStateOf(currentProfile.district) }

        AlertDialog(
            onDismissRequest = { showProfileDialog = false },
            title = { Text("Aspirant Profile") },
            text = {
                Column {
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        label = { Text("Your Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = districtInput,
                        onValueChange = { districtInput = it },
                        label = { Text("District (e.g. Srinagar, Jammu, Anantnag)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateProfile(nameInput, districtInput)
                        showProfileDialog = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showProfileDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
