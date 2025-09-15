package com.example.nycschools.domain.repository

import com.example.nycschools.data.remote.NetworkResult
import com.example.nycschools.domain.model.SchoolListing
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {

    suspend fun getSchoolListing(): Flow<NetworkResult<List<SchoolListing>>>
}