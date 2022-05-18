package com.aspire.rickandmortymvrx.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    //TODO: no need for KotlinJsonAdapterFactory() as long as you use Codegen, it's only need in case of Reflection

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
                //TODO: can be just MoshiConverterFactory.create() as long as you use Codegen
            .build()
    }

    val apiService: ApiService by lazy {

        retrofit.create(ApiService::class.java)

    }


}