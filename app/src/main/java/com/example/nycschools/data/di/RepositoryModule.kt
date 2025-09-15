package com.example.nycschools.data.di

import com.example.nycschools.data.repository.SchoolRepositoryImpl
import com.example.nycschools.domain.repository.SchoolRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        schoolRepositoryImpl: SchoolRepositoryImpl
    ): SchoolRepository
}