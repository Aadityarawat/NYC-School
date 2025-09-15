package com.example.nycschools.presentation.company_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nycschools.domain.model.SchoolListing

@Composable
fun SchoolInfoScreen(
    school: SchoolListing,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = school.school_name,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Email: ${school.school_email ?: "Not available"}")
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Location: ${school.location ?: "Not available"}")
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Phone: ${school.phone_number ?: "Not available"}")
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Sports: ${school.school_sports ?: "Not available"}")
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Extracurricular Activities: ${school.extracurricular_activities ?: "Not available"}")
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "SAT Scores", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Test Takers: ${school.num_of_sat_test_takers ?: "Not available"}")
        Text(text = "Math Avg Score: ${school.sat_math_avg_score ?: "Not available"}")
        Text(text = "Reading Avg Score: ${school.sat_critical_reading_avg_score ?: "Not available"}")
        Text(text = "Writing Avg Score: ${school.sat_writing_avg_score ?: "Not available"}")
    }
}
