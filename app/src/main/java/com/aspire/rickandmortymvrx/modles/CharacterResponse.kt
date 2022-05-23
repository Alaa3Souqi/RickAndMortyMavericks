package com.aspire.rickandmortymvrx.modles

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "results")
    val CharactersList: List<Character>
)