package com.aspire.rickandmortymvrx.ui.rickMorty

import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.aspire.rickandmortymvrx.mavericks.CharacterState
import com.aspire.rickandmortymvrx.modles.Character
import com.aspire.rickandmortymvrx.modles.CharacterResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RickMortyViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    @Test
    fun `fetch character when create view model`() {

        val charactersList = listOf(Character("alaa", ""))

        val repo = mockk<RickMortyRepository> {
            coEvery { getCharacters("1") } returns CharacterResponse(charactersList)
        }

        val viewModel = RickMortyViewModel(CharacterState(), repo)

        withState(viewModel) { state ->
            Assert.assertEquals(Success(CharacterResponse(charactersList)), state.state)
        }
        //nice work and you can also use viewModel.stateFlow.first()
    }
}