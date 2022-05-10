package com.aspire.rickandmortymvrx.ui.rickMorty

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.aspire.rickandmortymvrx.BaseApplication
import com.aspire.rickandmortymvrx.mavericks.CharacterState

class RickMortyViewModel(
    initialState: CharacterState,
    private val repository: RickMortyRepository,
) :
    MavericksViewModel<CharacterState>(initialState) {

    companion object : MavericksViewModelFactory<RickMortyViewModel, CharacterState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: CharacterState
        ): RickMortyViewModel {
            val repository = viewModelContext.app<BaseApplication>().rickMortyRepository
            return RickMortyViewModel(state, repository)
        }
    }

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
