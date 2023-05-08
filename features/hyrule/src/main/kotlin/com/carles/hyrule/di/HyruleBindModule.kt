package com.carles.hyrule.di

import com.carles.hyrule.data.HyruleRepo
import com.carles.hyrule.data.HyruleRepository
import com.carles.hyrule.data.HyruleRepositoryAlt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HyruleBindModule {

    @HyruleRepositoryQualifier
    @Binds
    @Singleton
    abstract fun bindHyruleRepository(repository: HyruleRepository): HyruleRepo

    @HyruleRepositoryAltQualifier
    @Binds
    @Singleton
    abstract fun bindHyruleRepositoryAlt(repository: HyruleRepositoryAlt): HyruleRepo
}