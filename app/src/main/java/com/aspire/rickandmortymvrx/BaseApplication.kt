package com.aspire.rickandmortymvrx

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.aspire.rickandmortymvrx.network.ApiClient
import com.aspire.rickandmortymvrx.ui.rickMorty.RickMortyRepository

class BaseApplication : Application() {
    val rickMortyRepository = RickMortyRepository(api = ApiClient.apiService)

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}