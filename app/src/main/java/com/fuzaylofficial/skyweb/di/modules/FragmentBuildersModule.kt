package com.fuzaylofficial.skyweb.di.modules

import com.fuzaylofficial.skyweb.ui.auth.AuthFragment
import dagger.android.ContributesAndroidInjector
import com.fuzaylofficial.skyweb.ui.home.HomeFragment
import dagger.Module

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    abstract fun contributeHomeFragment(): HomeFragment?

    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    abstract fun contributeAuthFragment(): AuthFragment?
}