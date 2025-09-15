package com.example.nycschools.domain.model

data class SchoolListing (
    val school_email: String?,
    val school_name: String,
    val overview_paragraph: String,
    val website: String,
    val location: String,
    val phone_number: String,
    val extracurricular_activities: String?,
    val school_sports: String?,
)