package com.aspire.rickandmortymvrx.ui.rickMorty

import com.aspire.rickandmortymvrx.network.ApiService

class RickMortyRepository(private val api: ApiService) {

    suspend fun getCharacters(page: String) =
        api.fetchCharacters(page)

}