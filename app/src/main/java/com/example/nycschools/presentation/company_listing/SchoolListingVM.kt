package com.example.nycschools.presentation.company_listing

import android.content.Context
import android.widget.Toast
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
): ViewModel(){
    var state by mutableStateOf(SchoolListingState())

    fun getSchoolListing(context: Context){
        viewModelScope.launch {
            repository.getSchoolListing()
                .collect {
                    when (it) {
                        is NetworkResult.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                        is NetworkResult.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                        is NetworkResult.Success -> {
                            state = state.copy(
                                schools = it.data!!,
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}