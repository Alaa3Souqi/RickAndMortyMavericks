package com.aspire.rickandmortymvrx.network
import com.aspire.rickandmortymvrx.modles.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String): CharacterResponse

}