package com.aspire.rickandmortymvrx.di

import com.airbnb.mvrx.MavericksViewModel
import dagger.Component

@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun viewModelFactories(): Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
}
