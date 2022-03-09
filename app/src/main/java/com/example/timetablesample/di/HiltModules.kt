package com.example.timetablesample.di

import com.example.timetablesample.common.Constants
import com.example.timetablesample.data.remote.TimeTableApi
import com.example.timetablesample.data.repository.TimeTableRepositoryImpl
import com.example.timetablesample.domain.repository.TimeTableRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                var requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("X-API-Authentication", "intervIEW_TOK3n")
                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyAPI(okHttpClient: OkHttpClient): TimeTableApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(TimeTableApi::class.java)
    }

    @Provides
    fun provideSearchRepository(timeTableApi: TimeTableApi): TimeTableRepository {
        return TimeTableRepositoryImpl(timeTableApi)
    }


}