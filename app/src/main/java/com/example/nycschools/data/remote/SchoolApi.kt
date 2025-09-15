package com.example.nycschools.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SchoolApi {

    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/"
    }

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolListing(): Response<T>
}