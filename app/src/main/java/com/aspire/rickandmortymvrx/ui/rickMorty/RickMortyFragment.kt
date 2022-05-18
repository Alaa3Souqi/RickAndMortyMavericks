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
    //TODO: avoid using lazy initializer with views related variables as they only live between onCreateView and onDestroyView and accessing them beyond that point could lead to memory leaks, use lateinit var instead
    private val viewModel: RickMortyViewModel by fragmentViewModel()
    private val adapter = GroupieAdapter()

    override fun invalidate() {
        withState(viewModel) {
            val state = it.state
            //TODO: it.state can be used directly without this extra variable
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
        //TODO: can be -> adapter.addAll(state.invoke().CharactersList.map{CharacterItems(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //TODO: add layout inflation logic here after converting `binding` to lateinit var
        return binding.root
    }

}