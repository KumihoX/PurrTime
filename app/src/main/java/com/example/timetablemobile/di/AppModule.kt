package com.example.timetablemobile.di

import com.example.timetablemobile.common.Constants
import com.example.timetablemobile.data.remote.AuthApi
import com.example.timetablemobile.data.remote.ScheduleApi
import com.example.timetablemobile.data.repository.AuthRepositoryImpl
import com.example.timetablemobile.data.repository.ScheduleRepositoryImpl
import com.example.timetablemobile.domain.repository.AuthRepository
import com.example.timetablemobile.domain.repository.ScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideScheduleApi(): ScheduleApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(api: ScheduleApi): ScheduleRepository {
        return ScheduleRepositoryImpl(api)
    }

}