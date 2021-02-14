package com.fuzaylofficial.skyweb.di.modules

import com.fuzaylofficial.skyweb.ui.repository.PicturesService
import com.fuzaylofficial.skyweb.ui.repository.PicturesRepository
import dagger.Module
import dagger.Provides

@Module
class PictureRepositoryModule {
    @Provides
    fun repoModule(service: PicturesService?): PicturesRepository {
        return PicturesRepository(service!!)
    }
}