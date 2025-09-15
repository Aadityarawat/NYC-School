package com.example.nycschools.domain.model

data class SchoolListing (
    val dbn: String,
    val school_email: String?,
    val school_name: String,
    val overview_paragraph: String?,
    val website: String?,
    val location: String?,
    val phone_number: String?,
    val extracurricular_activities: String?,
    val school_sports: String?,
    val num_of_sat_test_takers: String?,
    val sat_critical_reading_avg_score: String?,
    val sat_math_avg_score: String?,
    val sat_writing_avg_score: String?,
)