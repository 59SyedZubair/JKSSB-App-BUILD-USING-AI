package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuestionEntity
import com.example.data.model.SubjectRepository
import com.example.data.model.TestResultEntity
import java.util.Locale

@Composable
fun MockTestScreen(
    isActive: Boolean,
    questions: List<QuestionEntity>,
    currentIndex: Int,
    answers: Map<Long, Int>,
    markedForReview: Set<Long>,
    remainingSeconds: Int,
    latestResult: TestResultEntity?,
    onStartTest: (subjectId: String, count: Int, minutes: Int) -> Unit,
    onSelectAnswer: (questionId: Long, optionIndex: Int) -> Unit,
    onToggleMarkReview: (questionId: Long) -> Unit,
    onSelectIndex: (Int) -> Unit,
    onSubmitTest: () -> Unit,
    onDismissResult: () -> Unit
) {
    var showSubmitConfirmation by remember { mutableStateOf(false) }
    var selectedSubjectId by remember { mutableStateOf("all") }
    var selectedQuestionCount by remember { mutableIntStateOf(10) }
    var selectedMinutes by remember { mutableIntStateOf(15) }

    when {
        latestResult != null -> {
            MockTestResultView(result = latestResult, onDone = onDismissResult)
        }
        isActive && questions.isNotEmpty() -> {
            ActiveMockTestView(
                questions = questions,
                currentIndex = currentIndex,
                answers = answers,
                markedForReview = markedForReview,
                remainingSeconds = remainingSeconds,
                onSelectAnswer = onSelectAnswer,
                onToggleMarkReview = onToggleMarkReview,
                onSelectIndex = onSelectIndex,
                onRequestSubmit = { showSubmitConfirmation = true }
            )

            if (showSubmitConfirmation) {
                val answeredCount = answers.size
                val reviewCount = markedForReview.size
                val unattemptedCount = questions.size - answeredCount

                AlertDialog(
                    onDismissRequest = { showSubmitConfirmation = false },
                    title = { Text("Submit JKSSB Mock Test?") },
                    text = {
                        Column {
                            Text("Are you sure you want to finish and calculate your score?")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("• Answered: $answeredCount", color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                            Text("• Marked for Review: $reviewCount", color = Color(0xFFF59E0B))
                            Text("• Unattempted: $unattemptedCount", color = Color(0xFF64748B))
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                "Marking Scheme: +1.00 for Correct, -0.25 Negative Marking per wrong answer.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showSubmitConfirmation = false
                                onSubmitTest()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.testTag("confirm_submit_test_btn")
                        ) {
                            Text("Submit Test")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showSubmitConfirmation = false }) {
                            Text("Resume Test")
                        }
                    }
                )
            }
        }
        else -> {
            // Launcher Setup Screen
            MockTestLauncher(
                selectedSubjectId = selectedSubjectId,
                selectedCount = selectedQuestionCount,
                selectedMinutes = selectedMinutes,
                onSubjectChange = { selectedSubjectId = it },
                onCountChange = { selectedQuestionCount = it },
                onMinutesChange = { selectedMinutes = it },
                onLaunch = {
                    onStartTest(selectedSubjectId, selectedQuestionCount, selectedMinutes)
                }
            )
        }
    }
}

@Composable
fun MockTestLauncher(
    selectedSubjectId: String,
    selectedCount: Int,
    selectedMinutes: Int,
    onSubjectChange: (String) -> Unit,
    onCountChange: (Int) -> Unit,
    onMinutesChange: (Int) -> Unit,
    onLaunch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("mock_test_launcher")
    ) {
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "JKSSB Authentic Exam Simulator",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Experience standard JKSSB CBT conditions: Real-time countdown timer, question palette navigation, and official negative marking (-0.25 mark per incorrect answer).",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Exam Pattern Selector
        Text(
            text = "Select Test Subject",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (selectedSubjectId == "all") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onSubjectChange("all") }
                ) {
                    Text(
                        text = "Full Syllabus Mock",
                        color = if (selectedSubjectId == "all") Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    )
                }
            }
            itemsIndexed(SubjectRepository.subjects) { _, subject ->
                val isSelected = selectedSubjectId == subject.id
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) subject.primaryColor else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onSubjectChange(subject.id) }
                ) {
                    Text(
                        text = subject.shortCode,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Question Count
        Text(
            text = "Number of Questions",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf(5, 10, 15, 20).forEach { count ->
                val isSelected = selectedCount == count
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onCountChange(count) }
                ) {
                    Text(
                        text = "$count Qs",
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Time Limit
        Text(
            text = "Time Limit",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf(5, 10, 15, 30).forEach { mins ->
                val isSelected = selectedMinutes == mins
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onMinutesChange(mins) }
                ) {
                    Text(
                        text = "$mins mins",
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Marking Info Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFF59E0B), modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Official JKSSB Marking Guidelines", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("• Correct Answer: +1.00 Mark", fontSize = 12.sp, color = Color(0xFF10B981), fontWeight = FontWeight.SemiBold)
                Text("• Incorrect Answer: -0.25 Mark (Negative Marking)", fontSize = 12.sp, color = Color(0xFFF43F5E), fontWeight = FontWeight.SemiBold)
                Text("• Unattempted: 0.00 Marks", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onLaunch,
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("launch_mock_test_button")
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Start JKSSB Mock Test", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ActiveMockTestView(
    questions: List<QuestionEntity>,
    currentIndex: Int,
    answers: Map<Long, Int>,
    markedForReview: Set<Long>,
    remainingSeconds: Int,
    onSelectAnswer: (Long, Int) -> Unit,
    onToggleMarkReview: (Long) -> Unit,
    onSelectIndex: (Int) -> Unit,
    onRequestSubmit: () -> Unit
) {
    val currentQuestion = questions.getOrNull(currentIndex) ?: return
    val selectedOption = answers[currentQuestion.id]
    val isMarkedReview = markedForReview.contains(currentQuestion.id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("active_mock_test_view")
    ) {
        // Sticky Header with Timer & Submit Button
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Countdown Timer Pill
                val isUrgent = remainingSeconds < 120
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isUrgent) Color(0xFFFFE4E6) else MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Timer",
                            tint = if (isUrgent) Color(0xFFE11D48) else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = formatTestTime(remainingSeconds),
                            color = if (isUrgent) Color(0xFFE11D48) else MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }

                // Submit Button
                Button(
                    onClick = onRequestSubmit,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("submit_test_top_btn")
                ) {
                    Text("Submit", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Question Palette Bar
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(questions) { idx, q ->
                val isCurrent = idx == currentIndex
                val isAns = answers.containsKey(q.id)
                val isRev = markedForReview.contains(q.id)

                val bgColor = when {
                    isCurrent -> MaterialTheme.colorScheme.primary
                    isRev -> Color(0xFFF59E0B)
                    isAns -> Color(0xFF10B981)
                    else -> MaterialTheme.colorScheme.surface
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(bgColor)
                        .clickable { onSelectIndex(idx) }
                        .testTag("palette_q_$idx"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${idx + 1}",
                        color = if (isCurrent || isRev || isAns) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Active Question Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${currentIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (currentQuestion.pyqExamTag.isNotBlank()) {
                    Text(
                        text = currentQuestion.pyqExamTag,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = currentQuestion.questionText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Options (A, B, C, D)
            currentQuestion.getOptions().forEachIndexed { index, optionText ->
                val isChosen = selectedOption == index
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isChosen) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                    border = BorderStroke(
                        1.5.dp,
                        if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable { onSelectAnswer(currentQuestion.id, index) }
                        .testTag("mock_option_${currentQuestion.id}_$index")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isChosen) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (index) { 0 -> "A"; 1 -> "B"; 2 -> "C"; else -> "D" },
                                color = if (isChosen) Color.White else MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // Bottom Navigation Bar in Mock Test
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous Button
                OutlinedButton(
                    onClick = { onSelectIndex(currentIndex - 1) },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Prev")
                }

                // Mark for Review Button
                OutlinedButton(
                    onClick = { onToggleMarkReview(currentQuestion.id) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (isMarkedReview) Color(0xFFD97706) else MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Flag,
                        contentDescription = null,
                        tint = if (isMarkedReview) Color(0xFFD97706) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isMarkedReview) "Marked" else "Review")
                }

                // Next or Submit Button
                if (currentIndex < questions.size - 1) {
                    Button(
                        onClick = { onSelectIndex(currentIndex + 1) },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Next")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                } else {
                    Button(
                        onClick = onRequestSubmit,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                        modifier = Modifier.testTag("submit_test_final_btn")
                    ) {
                        Text("Finish")
                    }
                }
            }
        }
    }
}

@Composable
fun MockTestResultView(
    result: TestResultEntity,
    onDone: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .testTag("mock_test_result_view"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(Color(0xFF10B981).copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = Color(0xFF10B981),
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Test Completed!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = result.title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Big Score Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YOUR FINAL SCORE",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = String.format(Locale.getDefault(), "%.2f", result.score),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Out of ${result.maxScore.toInt()} Marks",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Breakdown Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ResultStatCard(
                label = "Correct",
                value = "+${result.correctCount}",
                color = Color(0xFF10B981),
                modifier = Modifier.weight(1f)
            )
            ResultStatCard(
                label = "Incorrect",
                value = "-${result.incorrectCount}",
                color = Color(0xFFF43F5E),
                modifier = Modifier.weight(1f)
            )
            ResultStatCard(
                label = "Unattempted",
                value = "${result.unattemptedCount}",
                color = Color(0xFF64748B),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ResultStatCard(
                label = "Accuracy",
                value = "${result.accuracy.toInt()}%",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            ResultStatCard(
                label = "Time Taken",
                value = formatTestTime(result.timeTakenSeconds),
                color = Color(0xFF8B5CF6),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onDone,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("dismiss_result_btn")
        ) {
            Text("Done / Practice More", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ResultStatCard(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.1f),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun formatTestTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return String.format(Locale.getDefault(), "%02d:%02d", mins, secs)
}
