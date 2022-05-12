package com.aspire.rickandmortymvrx.ui.rickMorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.aspire.rickandmortymvrx.databinding.FragmentRickMortyBinding
import com.aspire.rickandmortymvrx.modles.CharacterItems
import com.aspire.rickandmortymvrx.modles.CharacterResponse
import com.xwray.groupie.GroupieAdapter

class RickMortyFragment : Fragment(), MavericksView {

    private val binding by lazy { FragmentRickMortyBinding.inflate(layoutInflater) }
    private val viewModel: RickMortyViewModel by fragmentViewModel()
    private val adapter = GroupieAdapter()

    override fun invalidate() {
        withState(viewModel) {
            val state = it.result
            binding.progress.isVisible = state is Loading

            when (state) {
                is Success -> {
                    convertToGroupie(state)

                    binding.rvRickAndMorty.adapter = adapter

                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        state.error.message ?: "Try Again",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun convertToGroupie(state: Success<CharacterResponse>) {
        state.invoke().CharactersList.forEach {
            adapter.add(CharacterItems(it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}