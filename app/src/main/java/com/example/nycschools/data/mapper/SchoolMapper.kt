package com.example.nycschools.data.mapper

import com.example.nycschools.data.remote.dto.SATScoreDto
import com.example.nycschools.data.remote.dto.SchoolListingDto
import com.example.nycschools.domain.model.SchoolListing

fun SchoolListingDto.toSchoolListing(): SchoolListing {
    return SchoolListing(
        dbn = dbn,
        school_email = school_email,
        school_name = school_name,
        overview_paragraph = overview_paragraph,
        website = website,
        location = location,
        phone_number = phone_number,
        extracurricular_activities = extracurricular_activities,
        school_sports = school_sports,
        num_of_sat_test_takers = null,
        sat_critical_reading_avg_score = null,
        sat_math_avg_score = null,
        sat_writing_avg_score = null,
    )
}

fun SATScoreDto.toSchoolListing(): SchoolListing {
    return SchoolListing(
        dbn = dbn,
        school_name = "",
        school_email = null,
        overview_paragraph = null,
        website = null,
        location = null,
        phone_number = null,
        extracurricular_activities = null,
        school_sports = null,
        num_of_sat_test_takers = num_of_sat_test_takers,
        sat_math_avg_score = sat_math_avg_score,
        sat_writing_avg_score = sat_writing_avg_score,
        sat_critical_reading_avg_score = sat_critical_reading_avg_score
    )
}
