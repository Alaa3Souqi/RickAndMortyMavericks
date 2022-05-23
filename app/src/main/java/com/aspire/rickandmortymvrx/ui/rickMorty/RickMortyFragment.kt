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
import com.xwray.groupie.GroupieAdapter

class RickMortyFragment : Fragment(), MavericksView {

    private var _binding: FragmentRickMortyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RickMortyViewModel by fragmentViewModel()
    private val adapter = GroupieAdapter()

    override fun invalidate() {
        withState(viewModel) {

            binding.progress.isVisible = it.result is Loading

            when (it.result) {
                is Success -> {
                    adapter.addAll(it.result.invoke().CharactersList.map { i -> CharacterItems(i) })

                    binding.rvRickAndMorty.adapter = adapter

                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        it.result.error.message ?: "Try Again",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickMortyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
    }

}