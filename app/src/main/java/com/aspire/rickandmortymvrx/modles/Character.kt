package com.aspire.rickandmortymvrx.modles

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String
)