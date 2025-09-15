package com.example.nycschools.data.mapper

import com.example.nycschools.data.remote.dto.SchoolListingDao
import com.example.nycschools.domain.model.SchoolListing

fun SchoolListingDao.toSchoolListing(): SchoolListing {
    return SchoolListing(
        school_email = school_email,
        school_name = school_name,
        overview_paragraph = overview_paragraph,
        website = website,
        location = location,
        phone_number = phone_number,
        extracurricular_activities = extracurricular_activities,
        school_sports = school_sports,
    )
}