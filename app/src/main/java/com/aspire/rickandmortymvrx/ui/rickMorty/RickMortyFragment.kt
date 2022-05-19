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

    private var _binding: FragmentRickMortyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RickMortyViewModel by fragmentViewModel()
    private val adapter = GroupieAdapter()

    override fun invalidate() {
        withState(viewModel) {

            binding.progress.isVisible = it.state is Loading

            when (it.state) {
                is Success -> {
                    convertToGroupie(it.state)

                    binding.rvRickAndMorty.adapter = adapter

                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        it.state.error.message ?: "Try Again",
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
        //TODO: can be -> adapter.addAll(state.invoke().CharactersList.map{CharacterItems(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickMortyBinding.inflate(layoutInflater)
        return binding.root
    }

}