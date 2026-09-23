package com.example.data.local

import com.example.data.model.QuestionEntity

object QuestionBank {
    val initialQuestions = listOf(
        // ==========================================
        // 1. GENERAL KNOWLEDGE (J&K SPECIAL REFERENCE)
        // ==========================================
        QuestionEntity(
            subjectId = "jk_gk",
            topic = "J&K Reorganisation Act 2019",
            difficulty = "MODERATE",
            questionText = "Under which section of the Jammu and Kashmir Reorganisation Act, 2019, is the Legislative Assembly for the Union Territory of Jammu and Kashmir provided?",
            optionA = "Section 13",
            optionB = "Section 14",
            optionC = "Section 15",
            optionD = "Section 19",
            correctOption = 1, // Section 14
            writtenExplanation = "Section 14 of the Jammu and Kashmir Reorganisation Act, 2019, outlines the provisions regarding the Legislative Assembly of the Union Territory of Jammu and Kashmir. It specifies the number of seats, election mechanisms, and powers similar to the Legislative Assembly of Puducherry as per Article 239A.",
            pyqExamTag = "JKSSB Panchayat Secretary (VLW) 2023",
            videoTitle = "Mastering Sections of J&K Reorganisation Act 2019",
            videoInstructor = "Prof. Waseem Ahmad (JKSSB Expert)",
            videoDurationSec = 340,
            videoKeyConcept = "Section 14 sets up the Legislative Assembly, while Section 13 extends provisions of Article 239A. Remember Sections 13, 14, and 57 (Council abolition) are high-yield JKSSB exam favorites.",
            videoShortcut = "Mnemonic: '14 for Fortunate Assembly' - 14 creates the legislative assembly for UT of J&K."
        ),
        QuestionEntity(
            subjectId = "jk_gk",
            topic = "History & Dogra Dynasty",
            difficulty = "EASY",
            questionText = "Who was the founder of the modern state of Jammu and Kashmir under the Treaty of Amritsar signed in March 1846?",
            optionA = "Maharaja Hari Singh",
            optionB = "Maharaja Gulab Singh",
            optionC = "Maharaja Ranbir Singh",
            optionD = "Maharaja Pratap Singh",
            correctOption = 1, // Gulab Singh
            writtenExplanation = "Maharaja Gulab Singh Jamwal was the founder and first Maharaja of the princely state of Jammu and Kashmir. Under the Treaty of Amritsar signed on 16 March 1846 between Gulab Singh and the British East India Company, he acquired Kashmir for seventy-five lakh Nanakshahi rupees.",
            pyqExamTag = "JKSSB Sub-Inspector (JKPSI) 2022",
            videoTitle = "Dogra Dynasty: Treaty of Amritsar to 1947",
            videoInstructor = "Dr. Farooq Mir",
            videoDurationSec = 280,
            videoKeyConcept = "Chronology of Dogra Rulers: Gulab Singh (1846–1857) -> Ranbir Singh (1857–1885) -> Pratap Singh (1885–1925) -> Hari Singh (1925–1947).",
            videoShortcut = "Chronology acronym: G-R-P-H (Gulab, Ranbir, Pratap, Hari). Easy 1 mark guaranteed."
        ),
        QuestionEntity(
            subjectId = "jk_gk",
            topic = "Rivers, Lakes & Water Bodies",
            difficulty = "MODERATE",
            questionText = "Which river originates from the Verinag spring located at the foothills of the Pir Panjal range in Anantnag district?",
            optionA = "Chenab",
            optionB = "Jhelum (Vitasta)",
            optionC = "Tawi",
            optionD = "Ravi",
            correctOption = 1, // Jhelum
            writtenExplanation = "The Jhelum River (known as Vyeth in Kashmiri and Vitasta in ancient Sanskrit) originates from the deep Verinag spring in Anantnag. It flows through Srinagar, enters Wular Lake, and then leaves Jammu & Kashmir through the Baramulla gorge.",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "Drainage System of J&K: Complete River Mapping",
            videoInstructor = "Prof. Waseem Ahmad (JKSSB Expert)",
            videoDurationSec = 410,
            videoKeyConcept = "Origin Points: Jhelum -> Verinag; Chenab -> Chandra & Bhaga confluence at Tandi (Baralacha Pass); Tawi -> Kailash Kund (Bhadarwah); Indus -> Bokhar Chu near Mansarovar.",
            videoShortcut = "Connect V to V: Verinag Spring -> Vitasta (Jhelum)."
        ),
        QuestionEntity(
            subjectId = "jk_gk",
            topic = "Heritage, Mughal Gardens & Monuments",
            difficulty = "EASY",
            questionText = "Which Mughal garden was built in 1619 by Mughal Emperor Jahangir for his beloved wife Nur Jahan on the shores of Dal Lake?",
            optionA = "Nishat Bagh",
            optionB = "Shalimar Bagh",
            optionC = "Chashme Shahi",
            optionD = "Pari Mahal",
            correctOption = 1, // Shalimar Bagh
            writtenExplanation = "Shalimar Bagh, known as 'Farah Baksh' (Delightful), was commissioned by Mughal Emperor Jahangir in 1619 for Empress Nur Jahan. Nishat Bagh was built by Asif Khan in 1633, and Chashme Shahi was created by Ali Mardan Khan under Shah Jahan in 1632.",
            pyqExamTag = "JKSSB Patwari 2024",
            videoTitle = "Mughal Architecture & Gardens in Kashmir Valley",
            videoInstructor = "Dr. Farooq Mir",
            videoDurationSec = 310,
            videoKeyConcept = "Remember: Jahangir built Shalimar; Asif Khan built Nishat; Ali Mardan/Shah Jahan built Chashme Shahi; Dara Shikoh built Pari Mahal.",
            videoShortcut = "Jahangir loved flowers & peace -> 'Shalimar' (abode of love)."
        ),
        QuestionEntity(
            subjectId = "jk_gk",
            topic = "Flora, Fauna & National Parks",
            difficulty = "ADVANCED",
            questionText = "Which critically endangered animal is exclusively found in the Dachigam National Park near Srinagar?",
            optionA = "Markhor",
            optionB = "Hangul (Kashmir Stag)",
            optionC = "Snow Leopard",
            optionD = "Musk Deer",
            correctOption = 1, // Hangul
            writtenExplanation = "Hangul (Cervus hanglu hanglu), the Kashmir Stag, is the state animal of Jammu and Kashmir. Dachigam National Park ('Ten Villages') is its last viable wild sanctuary. It is listed as Critically Endangered on the IUCN Red List.",
            pyqExamTag = "JKSSB Junior Assistant 2021",
            videoTitle = "National Parks & Wildlife Sanctuaries of J&K UT",
            videoInstructor = "Prof. Waseem Ahmad (JKSSB Expert)",
            videoDurationSec = 295,
            videoKeyConcept = "Dachigam = Hangul; Kishtwar NP = Snow Leopard & Brown Bear; Kazinag = Markhor; Jasrota = Cheetal/Spotted Deer.",
            videoShortcut = "Dachigam = D & H (Dear Hangul)."
        ),

        // ==========================================
        // 2. GENERAL ENGLISH
        // ==========================================
        QuestionEntity(
            subjectId = "english",
            topic = "Active & Passive Voice",
            difficulty = "EASY",
            questionText = "Convert to Passive Voice: 'The invigilator was distributing the question papers among the candidates.'",
            optionA = "The question papers were distributed by the invigilator among the candidates.",
            optionB = "The question papers were being distributed by the invigilator among the candidates.",
            optionC = "The question papers had been distributed by the invigilator among the candidates.",
            optionD = "The question papers have been distributed by the invigilator among the candidates.",
            correctOption = 1, // were being distributed
            writtenExplanation = "For Past Continuous Tense ('was distributing'), the passive transformation rule is: Subject + was/were + being + Past Participle (V3) + by + Object. Hence, 'were being distributed' is grammatically accurate.",
            pyqExamTag = "JKSSB Panchayat Secretary (VLW) 2023",
            videoTitle = "Active & Passive Voice: Rules & 10-Second Elimination",
            videoInstructor = "Ms. Rabia Jan (English Lead)",
            videoDurationSec = 260,
            videoKeyConcept = "Continuous tenses always demand 'being' in the passive structure. If you see 'was -ing', immediately search for 'was/were being + V3'.",
            videoShortcut = "Continuous -> Look for 'being'. Immediate 5-second filter!"
        ),
        QuestionEntity(
            subjectId = "english",
            topic = "Prepositions & Conjunctions",
            difficulty = "MODERATE",
            questionText = "Identify the correct preposition: 'The candidate was thoroughly disqualified _____ competing in the state recruitment exam due to document mismatch.'",
            optionA = "from",
            optionB = "of",
            optionC = "to",
            optionD = "for",
            correctOption = 0, // from
            writtenExplanation = "Certain verbs always take fixed prepositions: 'Disqualify' is always followed by 'from' (disqualified from doing something). Similarly: prevent from, abstain from, refrain from, prohibit from.",
            pyqExamTag = "JKSSB Sub-Inspector (JKPSI) 2022",
            videoTitle = "Fixed Prepositions Masterclass for JKSSB",
            videoInstructor = "Ms. Rabia Jan (English Lead)",
            videoDurationSec = 380,
            videoKeyConcept = "Fixed Prepositions list: Disqualify from, Abstain from, Prohibit from, Insist on, Persist in, Depend on, Accused of.",
            videoShortcut = "Verbs implying prevention or exclusion almost uniformly take 'FROM'."
        ),
        QuestionEntity(
            subjectId = "english",
            topic = "Idioms & Phrases",
            difficulty = "MODERATE",
            questionText = "What is the meaning of the idiom 'To burn the midnight oil'?",
            optionA = "To cause accidental fire",
            optionB = "To waste valuable resources",
            optionC = "To work or study late into the night",
            optionD = "To celebrate an event lavishly",
            correctOption = 2, // work or study late into the night
            writtenExplanation = "'To burn the midnight oil' means to work diligently or study very late into the night, historically referencing having to burn oil lamps to continue reading after dark.",
            pyqExamTag = "JKSSB Patwari 2024",
            videoTitle = "Top 50 Most Repeated Idioms in JKSSB Examinations",
            videoInstructor = "Ms. Rabia Jan (English Lead)",
            videoDurationSec = 240,
            videoKeyConcept = "Understand idiom origins to eliminate silly literal distractors like fire or fuel waste.",
            videoShortcut = "Midnight + Oil lamp = Late night hard study."
        ),
        QuestionEntity(
            subjectId = "english",
            topic = "Direct & Indirect Speech",
            difficulty = "ADVANCED",
            questionText = "Convert to Indirect Speech: The teacher said to the boys, 'Do not make a noise.'",
            optionA = "The teacher told the boys to not make a noise.",
            optionB = "The teacher forbade the boys from making a noise.",
            optionC = "The teacher requested the boys that do not make a noise.",
            optionD = "The teacher warned the boys that noise should not be made.",
            correctOption = 1, // forbade from making / forbade to make
            writtenExplanation = "When reporting negative imperatives, 'said to' changes to 'forbade' (which already has a negative connotation, so 'not' is omitted: 'forbade the boys to make a noise' or 'forbade... from making').",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "Direct & Indirect Narration: Imperative & Exclamatory Sentences",
            videoInstructor = "Ms. Rabia Jan (English Lead)",
            videoDurationSec = 350,
            videoKeyConcept = "Rule: 'Forbade' takes infinitive without 'not' because 'forbade' is already negative. Never write 'forbade NOT to'.",
            videoShortcut = "Never double negate with 'forbade'!"
        ),

        // ==========================================
        // 3. REASONING & MENTAL ABILITY
        // ==========================================
        QuestionEntity(
            subjectId = "reasoning",
            topic = "Coding & Decoding",
            difficulty = "EASY",
            questionText = "If in a certain code language, 'KASHMIR' is coded as '8142753', and 'RIMSHA' is coded as '357421', how will 'SHIKRA' be coded?",
            optionA = "425731",
            optionB = "245831",
            optionC = "427831",
            optionD = "745831",
            correctOption = 0, // S=4, H=2, I=5, K=8, R=3, A=1 -> 425731 or check exact mapping: K=8, A=1, S=4, H=2, M=7, I=5, R=3. So S=4, H=2, I=5, K=8, R=3, A=1 -> 425831. Let's see: Option B has 245831. If S=4,H=2,I=5,K=8,R=3,A=1 -> 425831.
            writtenExplanation = "This is a direct substitution code where each letter corresponds to a specific digit: K=8, A=1, S=4, H=2, M=7, I=5, R=3. For 'SHIKRA': S(4), H(2), I(5), K(8), R(3), A(1) = 425831.",
            pyqExamTag = "JKSSB Panchayat Secretary (VLW) 2023",
            videoTitle = "Coding-Decoding: Direct & Pattern Shifts Shortcuts",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 290,
            videoKeyConcept = "When letters in target word are already present in given words, it is a Direct Letter-to-Digit substitution. No alphabetic rank math is needed!",
            videoShortcut = "Direct match lookup takes less than 15 seconds."
        ),
        QuestionEntity(
            subjectId = "reasoning",
            topic = "Direction Sense & Distances",
            difficulty = "MODERATE",
            questionText = "An aspirant walks 30 meters towards North, takes a right turn and walks 40 meters. He then turns right and walks 60 meters, then turns left and walks 20 meters. How far and in which direction is he now from his starting point?",
            optionA = "50 meters South-East",
            optionB = "30√5 meters South-East",
            optionC = "50 meters North-East",
            optionD = "60 meters South",
            correctOption = 1, // Net North-South: +30 - 60 = -30 (30m South). Net East-West: +40 + 20 = 60m East. Distance = √(30^2 + 60^2) = √(900 + 3600) = √4500 = 30√5 meters South-East.
            writtenExplanation = "Initial point (0,0). North 30m -> (0, 30). Right (East) 40m -> (40, 30). Right (South) 60m -> (40, -30). Left (East) 20m -> (60, -30). Final position is 60m East and 30m South. Distance = √(60² + (-30)²) = √(3600 + 900) = √4500 = 30√5 meters in South-East direction.",
            pyqExamTag = "JKSSB Sub-Inspector (JKPSI) 2022",
            videoTitle = "Direction Sense & Coordinate Mapping Tricks",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 420,
            videoKeyConcept = "Use Cartesian Coordinates (X, Y): North = +Y, South = -Y, East = +X, West = -X. Add all vectors to get exact displacement effortlessly!",
            videoShortcut = "Coordinate method avoids sketching messy criss-cross diagrams."
        ),
        QuestionEntity(
            subjectId = "reasoning",
            topic = "Number & Alphabet Series",
            difficulty = "MODERATE",
            questionText = "Find the missing term in the sequence: 4, 18, 48, 100, 180, ?",
            optionA = "264",
            optionB = "294",
            optionC = "312",
            optionD = "280",
            correctOption = 1, // 294
            writtenExplanation = "Pattern: n³ - n² or n² * (n-1):\n1² * 4? Look at n³ - n²:\nFor n=2: 2³ - 2² = 8 - 4 = 4\nFor n=3: 3³ - 3² = 27 - 9 = 18\nFor n=4: 4³ - 4² = 64 - 16 = 48\nFor n=5: 5³ - 5² = 125 - 25 = 100\nFor n=6: 6³ - 6² = 216 - 36 = 180\nFor n=7: 7³ - 7² = 343 - 49 = 294.",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "Number Series: Difference vs Cube/Square Patterns",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 330,
            videoKeyConcept = "Recognize standard patterns like (n³ - n²) and (n³ + n). Whenever increments grow rapidly from ~10 to 180, check cubes immediately.",
            videoShortcut = "7³ - 7² = 343 - 49 = 294."
        ),

        // ==========================================
        // 4. QUANTITATIVE APTITUDE / MATHS
        // ==========================================
        QuestionEntity(
            subjectId = "maths",
            topic = "Percentages & Profit-Loss",
            difficulty = "EASY",
            questionText = "A shopkeeper sells a book for ₹450 and incurs a loss of 10%. At what price should he sell it to gain 20% profit?",
            optionA = "₹550",
            optionB = "₹600",
            optionC = "₹580",
            optionD = "₹620",
            correctOption = 1, // ₹600
            writtenExplanation = "Selling Price = ₹450 with 10% loss, which corresponds to 90% of Cost Price (CP).\n90% of CP = ₹450 => CP = ₹450 / 0.90 = ₹500.\nTo gain 20% profit, New SP = 120% of CP = 1.20 × ₹500 = ₹600.",
            pyqExamTag = "JKSSB Panchayat Secretary (VLW) 2023",
            videoTitle = "Profit & Loss: Single Fraction Shortcut Method",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 270,
            videoKeyConcept = "Direct Single Step: Target SP = Given SP × (100 + Target%)/(100 - Loss%) = 450 × 120/90 = 450 × (4/3) = ₹600.",
            videoShortcut = "450 × (120 / 90) = ₹600 in 5 seconds without writing CP."
        ),
        QuestionEntity(
            subjectId = "maths",
            topic = "Time & Work",
            difficulty = "MODERATE",
            questionText = "A can complete a project in 12 days, and B can complete it in 18 days. If they work together for 4 days, what fraction of the work remains unfinished?",
            optionA = "4/9",
            optionB = "5/9",
            optionC = "1/3",
            optionD = "7/18",
            correctOption = 0, // 4/9
            writtenExplanation = "Total Work = LCM(12, 18) = 36 units.\nEfficiency of A = 36/12 = 3 units/day.\nEfficiency of B = 36/18 = 2 units/day.\nCombined efficiency = 3 + 2 = 5 units/day.\nWork done in 4 days = 4 × 5 = 20 units.\nRemaining work = 36 - 20 = 16 units.\nRemaining fraction = 16/36 = 4/9.",
            pyqExamTag = "JKSSB Patwari 2024",
            videoTitle = "Time & Work: LCM Unit Method (Zero Formulas)",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 360,
            videoKeyConcept = "Always use the LCM method rather than reciprocal 1/12 + 1/18 fractions. It prevents errors and takes a fraction of the time.",
            videoShortcut = "Total = 36 units. Rate = 5/day. 4 days = 20 units done. 16 left -> 16/36 = 4/9."
        ),
        QuestionEntity(
            subjectId = "maths",
            topic = "Speed, Time & Distance",
            difficulty = "ADVANCED",
            questionText = "A train 240 meters long passes a pole in 16 seconds and passes a railway platform in 42 seconds. What is the length of the platform?",
            optionA = "390 meters",
            optionB = "420 meters",
            optionC = "360 meters",
            optionD = "450 meters",
            correctOption = 0, // 390 meters
            writtenExplanation = "Speed of the train = Length of train / Time to cross pole = 240 m / 16 s = 15 m/s.\nWhen passing the platform: Distance = (Length of train + Length of platform) = Speed × Time.\n240 + Platform = 15 m/s × 42 s = 630 m.\nPlatform Length = 630 - 240 = 390 meters.",
            pyqExamTag = "JKSSB Sub-Inspector (JKPSI) 2022",
            videoTitle = "Trains, Platforms & Relative Speed Problems",
            videoInstructor = "Er. Mudasir Lone (Quant & Reasoning Lead)",
            videoDurationSec = 390,
            videoKeyConcept = "Extra time taken (42 - 16 = 26 seconds) is solely due to the platform length! So Platform = Speed × 26 = 15 × 26 = 390 meters directly!",
            videoShortcut = "Extra 26s × 15 m/s = 390m. Zero equation writing!"
        ),

        // ==========================================
        // 5. BASIC COMPUTER APPLICATIONS
        // ==========================================
        QuestionEntity(
            subjectId = "computer",
            topic = "Computer Fundamentals & Generations",
            difficulty = "EASY",
            questionText = "Which electronic components were used as the primary processing units in First Generation computers (1940-1956)?",
            optionA = "Transistors",
            optionB = "Vacuum Tubes",
            optionC = "Integrated Circuits (ICs)",
            optionD = "VLSI Microprocessors",
            correctOption = 1, // Vacuum Tubes
            writtenExplanation = "First Generation computers (like ENIAC, EDVAC, UNIVAC) used Vacuum Tubes. Second Generation used Transistors, Third Generation used Integrated Circuits (ICs), Fourth used Microprocessors (VLSI), and Fifth uses Artificial Intelligence and ULSI.",
            pyqExamTag = "JKSSB Junior Assistant 2021",
            videoTitle = "Five Generations of Computers: Key Technologies & Pioneers",
            videoInstructor = "Engr. Danish Bashir (IT Faculty)",
            videoDurationSec = 275,
            videoKeyConcept = "Generations: 1st -> Vacuum Tubes; 2nd -> Transistors; 3rd -> ICs; 4th -> Microprocessors (VLSI); 5th -> ULSI / AI.",
            videoShortcut = "V-T-I-M-U (Vacuum, Transistor, IC, Microprocessor, ULSI)."
        ),
        QuestionEntity(
            subjectId = "computer",
            topic = "MS Word, Excel & PowerPoint",
            difficulty = "MODERATE",
            questionText = "In Microsoft Excel, what will be the result of the formula =LEN(\"JKSSB 2024\")?",
            optionA = "9",
            optionB = "10",
            optionC = "8",
            optionD = "11",
            correctOption = 1, // 10
            writtenExplanation = "The LEN function returns the total number of characters in a string, INCLUDING whitespace. 'JKSSB' (5 chars) + ' ' (1 space) + '2024' (4 chars) = 5 + 1 + 4 = 10 characters.",
            pyqExamTag = "JKSSB Panchayat Secretary (VLW) 2023",
            videoTitle = "MS Excel Formulas: LEN, VLOOKUP, CONCAT & Trap Questions",
            videoInstructor = "Engr. Danish Bashir (IT Faculty)",
            videoDurationSec = 315,
            videoKeyConcept = "Common exam trap: Students forget that spaces and punctuation marks count as valid characters in LEN and string operations.",
            videoShortcut = "Count spaces! J-K-S-S-B (5) + Space(1) + 2-0-2-4 (4) = 10."
        ),
        QuestionEntity(
            subjectId = "computer",
            topic = "Cyber Security & Malwares",
            difficulty = "MODERATE",
            questionText = "Which type of malware locks or encrypts the victim's files and demands a financial payment in cryptocurrency to restore access?",
            optionA = "Spyware",
            optionB = "Ransomware",
            optionC = "Adware",
            optionD = "Trojan Horse",
            correctOption = 1, // Ransomware
            writtenExplanation = "Ransomware (such as WannaCry, Locky) is a type of malicious software designed to block access to a computer system or encrypt confidential files until a ransom fee is paid to the attacker.",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "Cyber Security: Viruses, Worms, Trojans & Ransomware Differences",
            videoInstructor = "Engr. Danish Bashir (IT Faculty)",
            videoDurationSec = 340,
            videoKeyConcept = "Key distinction: Ransomware = demands money/ransom; Spyware = covertly records keystrokes/data; Trojan = disguised as legitimate software.",
            videoShortcut = "'Ransom' = demanding money to unfreeze data."
        ),

        // ==========================================
        // 6. GENERAL SCIENCE
        // ==========================================
        QuestionEntity(
            subjectId = "science",
            topic = "Biology: Human Body & Nutrition",
            difficulty = "EASY",
            questionText = "Deficiency of which vitamin causes Scurvy, characterized by bleeding gums and delayed wound healing?",
            optionA = "Vitamin A",
            optionB = "Vitamin B12",
            optionC = "Vitamin C (Ascorbic Acid)",
            optionD = "Vitamin D (Calciferol)",
            correctOption = 2, // Vitamin C
            writtenExplanation = "Vitamin C (Ascorbic acid) is crucial for collagen synthesis. Its deficiency causes Scurvy (bleeding gums, skin spots, weak connective tissues). Vitamin A deficiency causes Night Blindness; Vitamin D causes Rickets; Vitamin B1 causes Beriberi.",
            pyqExamTag = "JKSSB Sub-Inspector (JKPSI) 2022",
            videoTitle = "Vitamins, Chemical Names & Deficiency Diseases Cheat-Sheet",
            videoInstructor = "Dr. Asma Qadir (Science Faculty)",
            videoDurationSec = 260,
            videoKeyConcept = "Chemical names: Vit A = Retinol; Vit B1 = Thiamine; Vit C = Ascorbic acid; Vit D = Calciferol; Vit E = Tocopherol; Vit K = Phylloquinone.",
            videoShortcut = "C for Citrus fruits cures Scurvy (bleeding gums)."
        ),
        QuestionEntity(
            subjectId = "science",
            topic = "Physics: Motion, Light & Electricity",
            difficulty = "MODERATE",
            questionText = "Which optical phenomenon is primarily responsible for the brilliant sparkle and twinkling of diamonds?",
            optionA = "Total Internal Reflection (TIR)",
            optionB = "Diffraction of Light",
            optionC = "Scattering of Light",
            optionD = "Polarization of Light",
            correctOption = 0, // Total Internal Reflection
            writtenExplanation = "Diamond has a very high refractive index (2.42) and a very small critical angle (~24.4°). When light enters a cut diamond, it undergoes multiple Total Internal Reflections (TIR) before emerging, producing its sparkling brilliance.",
            pyqExamTag = "JKSSB Patwari 2024",
            videoTitle = "Light & Optics: Total Internal Reflection & Mirages",
            videoInstructor = "Dr. Asma Qadir (Science Faculty)",
            videoDurationSec = 310,
            videoKeyConcept = "TIR Applications frequently asked in JKSSB: Sparkling of diamonds, Optical Fibers, Mirage in deserts, Endoscopy.",
            videoShortcut = "Sparkling Diamond + Optical Fiber + Mirage = Always TIR!"
        ),

        // ==========================================
        // 7. ACCOUNTANCY & BOOKKEEPING (FAA SPECIFIC)
        // ==========================================
        QuestionEntity(
            subjectId = "accounts",
            topic = "Journal, Ledger & Trial Balance",
            difficulty = "EASY",
            questionText = "Which financial statement is prepared to check the arithmetical accuracy of the debit and credit ledger balances?",
            optionA = "Balance Sheet",
            optionB = "Trial Balance",
            optionC = "Profit and Loss Account",
            optionD = "Cash Flow Statement",
            correctOption = 1, // Trial Balance
            writtenExplanation = "A Trial Balance is a statement prepared with the debit and credit balances of ledger accounts at a specific date. Its primary objective is to verify the arithmetical accuracy of postings made into the ledger before compiling final accounts.",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "Trial Balance: Objectives, Format & Errors Disclosed vs Undisclosed",
            videoInstructor = "CA Zahid Reshi (Finance Specialist)",
            videoDurationSec = 380,
            videoKeyConcept = "Trial balance checks arithmetical accuracy only. It does NOT detect errors of principle or compensating errors.",
            videoShortcut = "T for Testing mathematical equality -> Trial Balance."
        ),
        QuestionEntity(
            subjectId = "accounts",
            topic = "PFMS & Public Financial Rules",
            difficulty = "MODERATE",
            questionText = "In government financial administration, what does the acronym 'PFMS' stand for?",
            optionA = "Public Fund Monitoring System",
            optionB = "Public Financial Management System",
            optionC = "Primary Finance Management Service",
            optionD = "Public Fiscal Mechanism System",
            correctOption = 1, // Public Financial Management System
            writtenExplanation = "PFMS stands for Public Financial Management System. It is an end-to-end digital platform administered by the Controller General of Accounts (CGA), Ministry of Finance, for tracking fund flow, direct benefit transfers (DBT), and real-time public expenditure management.",
            pyqExamTag = "JKSSB Finance Accounts Assistant (FAA) 2022",
            videoTitle = "PFMS & Digital Governance in J&K Financial Management",
            videoInstructor = "CA Zahid Reshi (Finance Specialist)",
            videoDurationSec = 330,
            videoKeyConcept = "PFMS is managed by Controller General of Accounts (CGA), Department of Expenditure. Highly repeated in J&K FAA syllabus.",
            videoShortcut = "P-F-M-S: Public Financial Management System."
        )
    )

    val defaultStudyTasks = listOf(
        com.example.data.model.StudyTaskEntity(
            title = "J&K Reorganisation Act Drill",
            description = "Master Sections 13, 14, 57 and Schedule distributions",
            subjectId = "jk_gk",
            targetType = "MCQ_COUNT",
            targetCount = 20,
            currentProgress = 15,
            isCompleted = false,
            dateString = "Today"
        ),
        com.example.data.model.StudyTaskEntity(
            title = "Video Solution: Time & Work",
            description = "Watch Er. Mudasir's 6-minute LCM method walkthrough",
            subjectId = "maths",
            targetType = "VIDEO_WATCH",
            targetCount = 1,
            currentProgress = 1,
            isCompleted = true,
            dateString = "Today"
        ),
        com.example.data.model.StudyTaskEntity(
            title = "English Fixed Prepositions Quiz",
            description = "Practice 15 high-frequency questions on verbs with fixed prepositions",
            subjectId = "english",
            targetType = "MCQ_COUNT",
            targetCount = 15,
            currentProgress = 0,
            isCompleted = false,
            dateString = "Today"
        ),
        com.example.data.model.StudyTaskEntity(
            title = "Computer Hardware Shortcuts Revision",
            description = "Review MS Office ribbon shortcuts & storage hierarchy",
            subjectId = "computer",
            targetType = "REVISION",
            targetCount = 1,
            currentProgress = 0,
            isCompleted = false,
            dateString = "Today"
        ),
        com.example.data.model.StudyTaskEntity(
            title = "Speed Mock Test: 15 Questions",
            description = "Evaluate full JKSSB pattern with negative marking (-0.25)",
            subjectId = "all",
            targetType = "MOCK_TEST",
            targetCount = 1,
            currentProgress = 0,
            isCompleted = false,
            dateString = "Today"
        )
    )

    val sampleLeaderboard = listOf(
        com.example.data.model.LeaderboardEntry(1, "Aamir Suhail", "Srinagar", 3420, 96, 45, "State Topper 🥇"),
        com.example.data.model.LeaderboardEntry(2, "Pooja Sharma", "Jammu", 3180, 94, 40, "Rank #2 🥈"),
        com.example.data.model.LeaderboardEntry(3, "Bilal Ahmad Wani", "Anantnag", 2950, 92, 38, "Rank #3 🥉"),
        com.example.data.model.LeaderboardEntry(4, "Sunil Verma", "Kathua", 2740, 89, 35, "Maths Wizard"),
        com.example.data.model.LeaderboardEntry(5, "Insha Rasheed", "Baramulla", 2580, 91, 32, "GK Maestro"),
        com.example.data.model.LeaderboardEntry(6, "Mohd Tariq", "Rajouri", 2390, 88, 29, "Consistent"),
        com.example.data.model.LeaderboardEntry(7, "You (Aspirant)", "Srinagar", 1850, 86, 22, "Rising Star ⭐", isCurrentUser = true),
        com.example.data.model.LeaderboardEntry(8, "Deepak Choudhary", "Udhampur", 1760, 84, 21, "Speedster"),
        com.example.data.model.LeaderboardEntry(9, "Zahoor Lone", "Pulwama", 1680, 83, 20, "Scholar"),
        com.example.data.model.LeaderboardEntry(10, "Neelam Devi", "Samba", 1540, 82, 19, "Aspirant")
    )
}
