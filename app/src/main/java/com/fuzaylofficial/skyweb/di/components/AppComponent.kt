package com.fuzaylofficial.skyweb.di.components

import android.app.Application
import com.fuzaylofficial.skyweb.di.App
import com.fuzaylofficial.skyweb.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class)
    ,(AppModule::class)
    ,(FragmentBuildersModule::class)
    ,(NetworkModule::class)
    ,(ViewModelsModule::class)
    ,(PictureRepositoryModule::class)])

interface AppComponent : AndroidInjector<App> {

    override fun inject(instance: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}