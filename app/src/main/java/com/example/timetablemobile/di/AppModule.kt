package com.example.timetablemobile.di

import com.example.timetablemobile.common.Constants
import com.example.timetablemobile.data.remote.AuthApi
import com.example.timetablemobile.data.remote.ClassroomApi
import com.example.timetablemobile.data.remote.GroupApi
import com.example.timetablemobile.data.remote.TeacherApi
import com.example.timetablemobile.data.repository.AuthRepositoryImpl
import com.example.timetablemobile.data.repository.ClassroomRepositoryImpl
import com.example.timetablemobile.data.repository.GroupRepositoryImpl
import com.example.timetablemobile.data.repository.TeacherRepositoryImpl
import com.example.timetablemobile.domain.repository.AuthRepository
import com.example.timetablemobile.domain.repository.ClassroomRepository
import com.example.timetablemobile.domain.repository.GroupRepository
import com.example.timetablemobile.domain.repository.TeacherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder().apply {
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)

        val logLevel = HttpLoggingInterceptor.Level.BODY
        addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
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
    fun provideClassroomApi(): ClassroomApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ClassroomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(api: ClassroomApi): ClassroomRepository {
        return ClassroomRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGroupApi(): GroupApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(GroupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupRepository(api: GroupApi): GroupRepository {
        return GroupRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTeacherApi(): TeacherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(TeacherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTeacherRepository(api: TeacherApi): TeacherRepository {
        return TeacherRepositoryImpl(api)
    }

}