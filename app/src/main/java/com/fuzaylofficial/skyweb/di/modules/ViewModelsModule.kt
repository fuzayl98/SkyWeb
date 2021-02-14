package com.fuzaylofficial.skyweb.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuzaylofficial.skyweb.di.ViewModelFactory
import com.fuzaylofficial.skyweb.di.keys.ViewModelKey
import com.fuzaylofficial.skyweb.ui.auth.AuthViewModel
import com.fuzaylofficial.skyweb.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(modelProvider: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    protected abstract fun authViewModel(authViewModel: AuthViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun homeViewModel(homeViewModel: HomeViewModel?): ViewModel?

}
