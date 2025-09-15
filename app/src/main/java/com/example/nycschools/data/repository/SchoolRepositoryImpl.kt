package com.example.nycschools.data.repository

import android.util.Log
import com.example.nycschools.data.mapper.toSchoolListing
import com.example.nycschools.data.remote.NetworkResult
import com.example.nycschools.data.remote.SchoolApi
import com.example.nycschools.domain.model.SchoolListing
import com.example.nycschools.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolRepositoryImpl @Inject constructor(val schoolApi: SchoolApi): SchoolRepository{
    override suspend fun getSchoolListing(): Flow<NetworkResult<List<SchoolListing>>> {
        return flow {
            emit(NetworkResult.Loading())

            try {
                val schoolResponse = schoolApi.getSchoolListing()
                val schoolList = schoolResponse.body()?.map { it.toSchoolListing() } ?: emptyList()

                val satResponse = schoolApi.getSATScore()
                val satList = satResponse.body()?.map { it.toSchoolListing() } ?: emptyList()

                val satMap = satList.associateBy { it.dbn }

                val combinedList = schoolList.map { school ->
                    val satData = satMap[school.dbn]
                    school.copy(
                        sat_math_avg_score = satData?.sat_math_avg_score,
                        sat_writing_avg_score = satData?.sat_writing_avg_score,
                        sat_critical_reading_avg_score = satData?.sat_critical_reading_avg_score,
                        num_of_sat_test_takers = satData?.num_of_sat_test_takers
                    )
                }

                emit(NetworkResult.Success(combinedList))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResult.Error("Network error: ${e.message}"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResult.Error("HTTP error: ${e.message}"))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error("Unknown error: ${e.message}"))
            }
        }
    }

}