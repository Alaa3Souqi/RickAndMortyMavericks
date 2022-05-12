package com.aspire.rickandmortymvrx.di

import com.aspire.rickandmortymvrx.ui.rickMorty.RickMortyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RickMortyViewModel::class)
    fun helloViewModelFactory(factory: RickMortyViewModel.Factory): AssistedViewModelFactory<*, *>

}
