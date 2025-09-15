package com.example.nycschools.data.repository

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
                val response = schoolApi.getSchoolListing()
                response.body()?.let { it ->
                    val data = it.map { it1 ->
                        it1.toSchoolListing()
                    }

                    emit(NetworkResult.Success(data))

                }
            }catch (e : IOException){
                e.printStackTrace()
                emit(NetworkResult.Error("${e.message}"))
                null
            }catch (e : HttpException){
                e.printStackTrace()
                emit(NetworkResult.Error("${e.message}"))
                null
            }

        }
    }
}