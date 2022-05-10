package com.aspire.rickandmortymvrx.mavericks

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.aspire.rickandmortymvrx.modles.CharacterResponse

data class CharacterState(
    val state: Async<CharacterResponse> = Uninitialized
) : MavericksState
