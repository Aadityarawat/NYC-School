package com.example.nycschools.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.data.remote.NetworkResult
import com.example.nycschools.domain.repository.SchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListingVM @Inject constructor(
    private val repository: SchoolRepository
): ViewModel() {

    var state by mutableStateOf(SchoolListingState())
        private set

    init {
        getSchoolListing()
    }

    fun getSchoolListing() {
        viewModelScope.launch {
            repository.getSchoolListing()
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message ?: "Something went wrong"
                            )
                        }
                        is NetworkResult.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                        is NetworkResult.Success -> {
                            state = state.copy(
                                schools = result.data ?: emptyList(),
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }
        }
    }
}
