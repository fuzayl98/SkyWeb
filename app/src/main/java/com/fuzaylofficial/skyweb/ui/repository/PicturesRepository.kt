package com.fuzaylofficial.skyweb.ui.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.fuzaylofficial.skyweb.model.Picture
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PicturesRepository @Inject constructor(private val picturesApiService: PicturesService) {


    fun letImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Picture>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                PicturesPagingSource(
                    picturesApiService
                )
            }
        ).flow
    }

    /**Для пользователей RxJava*/
    fun letImagesObservable(pagingConfig: PagingConfig = getDefaultPageConfig()): Observable<PagingData<Picture>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                PicturesPagingSource(
                    picturesApiService
                )
            }
        ).observable
    }

    /** Для пользователей liveData*/
    fun letImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Picture>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                PicturesPagingSource(
                    picturesApiService
                )
            }
        ).liveData
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = Companion.DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }


}