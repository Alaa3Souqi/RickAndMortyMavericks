package com.aspire.rickandmortymvrx.modles

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.aspire.rickandmortymvrx.R
import com.aspire.rickandmortymvrx.databinding.RickAndMortyItemBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CharacterItems(
    val character: Character,
) :
    BindableItem<RickAndMortyItemBinding>() {

    override fun bind(viewBinding: RickAndMortyItemBinding, position: Int) {
        viewBinding.apply {
            tvCharacterName.text = character.name
            ivCharacter.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun getLayout(): Int = R.layout.rick_and_morty_item

    override fun initializeViewBinding(view: View): RickAndMortyItemBinding =
        RickAndMortyItemBinding.bind(view)
}