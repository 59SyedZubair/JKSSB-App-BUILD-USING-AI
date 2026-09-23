package com.example.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Science
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class ExamCategory(val displayName: String, val code: String) {
    VLW_PANCHAYAT("JKSSB Panchayat Secretary (VLW)", "VLW"),
    FAA("JKSSB Finance Accounts Assistant", "FAA"),
    SUB_INSPECTOR("JKSSB Sub-Inspector (Home Dept)", "JKPSI"),
    PATWARI("JKSSB Patwari (Revenue)", "PATWARI"),
    JUNIOR_ASSISTANT("JKSSB Junior Assistant", "JA")
}

data class SubjectInfo(
    val id: String,
    val name: String,
    val shortCode: String,
    val icon: ImageVector,
    val primaryColor: Color,
    val topics: List<String>
)

object SubjectRepository {
    val subjects = listOf(
        SubjectInfo(
            id = "jk_gk",
            name = "General Knowledge (J&K Special)",
            shortCode = "J&K GK",
            icon = Icons.Default.Landscape,
            primaryColor = Color(0xFF0284C7),
            topics = listOf(
                "All Topics",
                "J&K Reorganisation Act 2019",
                "History & Dogra Dynasty",
                "Rivers, Lakes & Water Bodies",
                "Geography, Climate & Soils",
                "Heritage, Mughal Gardens & Monuments",
                "Flora, Fauna & National Parks",
                "Panchayati Raj Act & Schemes"
            )
        ),
        SubjectInfo(
            id = "english",
            name = "General English",
            shortCode = "English",
            icon = Icons.Default.MenuBook,
            primaryColor = Color(0xFF8B5CF6),
            topics = listOf(
                "All Topics",
                "Active & Passive Voice",
                "Direct & Indirect Speech",
                "Prepositions & Conjunctions",
                "Idioms & Phrases",
                "Synonyms & Antonyms",
                "Sentence Correction & Error Spotting",
                "Cloze Test & Comprehension"
            )
        ),
        SubjectInfo(
            id = "reasoning",
            name = "Reasoning & Mental Ability",
            shortCode = "Reasoning",
            icon = Icons.Default.Psychology,
            primaryColor = Color(0xFFEC4899),
            topics = listOf(
                "All Topics",
                "Coding & Decoding",
                "Number & Alphabet Series",
                "Blood Relations",
                "Direction Sense & Distances",
                "Syllogisms & Logical Venn",
                "Seating Arrangement & Puzzles"
            )
        ),
        SubjectInfo(
            id = "maths",
            name = "Quantitative Aptitude",
            shortCode = "Maths",
            icon = Icons.Default.Calculate,
            primaryColor = Color(0xFFF59E0B),
            topics = listOf(
                "All Topics",
                "Percentages & Profit-Loss",
                "Ratio & Proportion",
                "Time & Work",
                "Speed, Time & Distance",
                "Simple & Compound Interest",
                "Mensuration & Geometry"
            )
        ),
        SubjectInfo(
            id = "computer",
            name = "Basic Computer Applications",
            shortCode = "Computers",
            icon = Icons.Default.Computer,
            primaryColor = Color(0xFF10B981),
            topics = listOf(
                "All Topics",
                "Computer Fundamentals & Generations",
                "Hardware, CPU & Memory Types",
                "Operating Systems & Keyboard Shortcuts",
                "MS Word, Excel & PowerPoint",
                "Internet, Email & Networking",
                "Cyber Security & Malwares"
            )
        ),
        SubjectInfo(
            id = "science",
            name = "General Science",
            shortCode = "Science",
            icon = Icons.Default.Science,
            primaryColor = Color(0xFF14B8A6),
            topics = listOf(
                "All Topics",
                "Physics: Motion, Light & Electricity",
                "Chemistry: Common Chemicals & Acids",
                "Biology: Human Body & Nutrition",
                "Ecology, Biodiversity & Environment"
            )
        ),
        SubjectInfo(
            id = "accounts",
            name = "Accountancy & Bookkeeping (FAA)",
            shortCode = "Accounts",
            icon = Icons.Default.ReceiptLong,
            primaryColor = Color(0xFFEA580C),
            topics = listOf(
                "All Topics",
                "Accounting Principles & Concepts",
                "Journal, Ledger & Trial Balance",
                "Cash Book & Bank Reconciliation",
                "Financial Management & Budgeting",
                "PFMS & Public Financial Rules"
            )
        )
    )
}
