package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.model.Difficulty
import com.example.data.model.QuestionEntity
import com.example.data.model.SubjectRepository
import com.example.ui.viewmodel.QuestionFilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McqPracticeScreen(
    questions: List<QuestionEntity>,
    selectedSubjectId: String,
    selectedTopic: String,
    selectedDifficulty: Difficulty,
    selectedFilterType: QuestionFilterType,
    searchQuery: String,
    onSelectSubject: (String) -> Unit,
    onSelectTopic: (String) -> Unit,
    onSelectDifficulty: (Difficulty) -> Unit,
    onSelectFilterType: (QuestionFilterType) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onOptionSelected: (QuestionEntity, Int) -> Unit,
    onToggleBookmark: (QuestionEntity) -> Unit,
    onOpenVideoSolution: (QuestionEntity) -> Unit
) {
    var topicDropdownExpanded by remember { mutableStateOf(false) }

    // Find topics for the currently selected subject
    val currentSubject = SubjectRepository.subjects.find { it.id == selectedSubjectId }
    val availableTopics = currentSubject?.topics ?: listOf("All Topics")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("mcq_practice_screen")
    ) {
        // Top Filter Bar & Search
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search MCQs, topics, sections (e.g. Jhelum, Reorganisation)") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_mcqs_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subject Filter Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = selectedSubjectId == "all",
                        onClick = { onSelectSubject("all") },
                        label = { Text("All Subjects") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("filter_subject_all")
                    )
                }
                items(SubjectRepository.subjects) { subject ->
                    FilterChip(
                        selected = selectedSubjectId == subject.id,
                        onClick = { onSelectSubject(subject.id) },
                        label = { Text(subject.shortCode) },
                        leadingIcon = {
                            Icon(
                                imageVector = subject.icon,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = subject.primaryColor,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("filter_subject_${subject.id}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Topic Selector & Difficulty Chips Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Topic dropdown trigger
                Box {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.clickable { topicDropdownExpanded = true }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = selectedTopic,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = topicDropdownExpanded,
                        onDismissRequest = { topicDropdownExpanded = false }
                    ) {
                        availableTopics.forEach { topic ->
                            DropdownMenuItem(
                                text = { Text(topic) },
                                onClick = {
                                    onSelectTopic(topic)
                                    topicDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Difficulty Chips
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Difficulty.values().forEach { diff ->
                        val isSelected = selectedDifficulty == diff
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable { onSelectDifficulty(diff) }
                        ) {
                            Text(
                                text = when (diff) {
                                    Difficulty.ALL -> "All"
                                    Difficulty.EASY -> "Easy"
                                    Difficulty.MODERATE -> "Med"
                                    Difficulty.ADVANCED -> "Exam Level"
                                },
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Filter Type (All, PYQs, Starred, Mistakes)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(QuestionFilterType.values()) { type ->
                    val isSelected = selectedFilterType == type
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                        else Color.Transparent,
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) MaterialTheme.colorScheme.secondary
                            else MaterialTheme.colorScheme.outlineVariant
                        ),
                        modifier = Modifier.clickable { onSelectFilterType(type) }
                    ) {
                        Text(
                            text = type.label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Questions Count Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${questions.size} Questions Available",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )
            val solvedCount = questions.count { it.userSelectedOption != null }
            Text(
                text = "$solvedCount Attempted",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        // Questions List
        if (questions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No questions match your filter",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Try switching subject, topic, or clearing search query",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(questions, key = { it.id }) { question ->
                    QuestionPracticeCard(
                        question = question,
                        onOptionSelected = { optionIndex ->
                            onOptionSelected(question, optionIndex)
                        },
                        onToggleBookmark = {
                            onToggleBookmark(question)
                        },
                        onOpenVideoSolution = {
                            onOpenVideoSolution(question)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionPracticeCard(
    question: QuestionEntity,
    onOptionSelected: (Int) -> Unit,
    onToggleBookmark: () -> Unit,
    onOpenVideoSolution: () -> Unit
) {
    val options = question.getOptions()
    val isAnswered = question.userSelectedOption != null

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("question_card_${question.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Tags & Bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // PYQ Tag Badge
                    if (question.pyqExamTag.isNotBlank()) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "PYQ: ${question.pyqExamTag}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    // Difficulty Tag
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = when (question.difficulty) {
                            "EASY" -> Color(0xFFD1FAE5)
                            "MODERATE" -> Color(0xFFFEF3C7)
                            else -> Color(0xFFFFE4E6)
                        }
                    ) {
                        Text(
                            text = question.difficulty,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = when (question.difficulty) {
                                "EASY" -> Color(0xFF065F46)
                                "MODERATE" -> Color(0xFF92400E)
                                else -> Color(0xFF9F1239)
                            },
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier.size(32.dp).testTag("bookmark_btn_${question.id}")
                ) {
                    Icon(
                        imageVector = if (question.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (question.isBookmarked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Question Text
            Text(
                text = question.questionText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Options (A, B, C, D)
            options.forEachIndexed { index, optionText ->
                val optionLabel = when (index) {
                    0 -> "A"
                    1 -> "B"
                    2 -> "C"
                    else -> "D"
                }

                val isSelectedByUser = question.userSelectedOption == index
                val isCorrectOption = question.correctOption == index

                val backgroundColor = when {
                    !isAnswered -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    isCorrectOption -> Color(0xFFD1FAE5) // Soft Emerald
                    isSelectedByUser && !isCorrectOption -> Color(0xFFFFE4E6) // Soft Rose
                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                }

                val borderColor = when {
                    !isAnswered -> Color.Transparent
                    isCorrectOption -> Color(0xFF10B981)
                    isSelectedByUser && !isCorrectOption -> Color(0xFFF43F5E)
                    else -> Color.Transparent
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = backgroundColor,
                    border = BorderStroke(1.dp, borderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable(enabled = !isAnswered) {
                            onOptionSelected(index)
                        }
                        .testTag("option_${question.id}_$index")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                                        isCorrectOption -> Color(0xFF10B981)
                                        isSelectedByUser && !isCorrectOption -> Color(0xFFF43F5E)
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isAnswered && isCorrectOption) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            } else if (isAnswered && isSelectedByUser && !isCorrectOption) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            } else {
                                Text(
                                    text = optionLabel,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelectedByUser || (isAnswered && isCorrectOption)) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Explanation & Video Solution Button (Shown when answered)
            AnimatedVisibility(visible = isAnswered) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    // Written Explanation Card
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Detailed Written Explanation",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = question.writtenExplanation,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Video Solution Launcher Button
                    Button(
                        onClick = onOpenVideoSolution,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("watch_video_btn_${question.id}")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayCircleFilled,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Watch Video Solution (${question.videoInstructor})",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
