package com.aspire.rickandmortymvrx.di

import com.aspire.rickandmortymvrx.network.ApiService
import com.aspire.rickandmortymvrx.network.Constants.RICK_MORTY_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder().add(
                KotlinJsonAdapterFactory()
            ).build()
        )

    @Singleton
    @Provides
    fun provideRetrofit(moshi: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(RICK_MORTY_URL)
            .addConverterFactory(moshi)
            .build()

    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
