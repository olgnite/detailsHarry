package com.example.harry_details.di

import android.app.Application
import android.content.Context
import com.example.harry_details.data.CharacterRepository
import com.example.harry_details.data.CharacterRepositoryImpl
import com.example.harry_details.db.CharacterDao
import com.example.harry_details.db.HarryPotterDatabase
import com.example.harry_details.network.HarryPoterInstance
import com.example.harry_details.network.HarryPotterApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailsModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + CoroutineName("SingletonScope"))
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
    ): HarryPotterDatabase {
        return HarryPotterDatabase.create(application)
    }

    @Provides
    @Singleton
    fun provideDao(
        database: HarryPotterDatabase,
    ): CharacterDao {
        return database.getCharacterDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): HarryPotterApiService {
        return HarryPoterInstance.api
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface DetailsBindsModule {

    @Binds
    fun bindRepository(impl: CharacterRepositoryImpl): CharacterRepository
}