package com.aspire.rickandmortymvrx.ui.rickMorty

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.aspire.rickandmortymvrx.BaseApplication
import com.aspire.rickandmortymvrx.di.AssistedViewModelFactory
import com.aspire.rickandmortymvrx.di.daggerMavericksViewModelFactory
import com.aspire.rickandmortymvrx.mavericks.CharacterState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RickMortyViewModel @AssistedInject constructor(
    @Assisted initialState: CharacterState,
    private val repository: RickMortyRepository,
) :
    MavericksViewModel<CharacterState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<RickMortyViewModel, CharacterState> {
        override fun create(state: CharacterState): RickMortyViewModel
    }

    companion object : MavericksViewModelFactory<RickMortyViewModel, CharacterState> by daggerMavericksViewModelFactory()

    init {
        getData()
    }

    private fun getData() {
        suspend {
            repository.getCharacters("1")
        }.execute {
            copy(state = it)
        }
    }
}
