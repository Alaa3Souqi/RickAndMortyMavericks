package com.aspire.rickandmortymvrx.ui.rickMorty

import com.aspire.rickandmortymvrx.network.ApiService
import javax.inject.Inject

class RickMortyRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getCharacters(page: String) =
        api.fetchCharacters(page)

}
