package com.example.nycschools.presentation.company_listing

import com.example.nycschools.domain.model.SchoolListing

data class SchoolListingState (
    val schools: List<SchoolListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)