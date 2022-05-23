package com.aspire.rickandmortymvrx.modles

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "results")
    val CharactersList: List<Character>
)