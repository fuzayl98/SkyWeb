package com.fuzaylofficial.skyweb.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.fuzaylofficial.skyweb.model.Picture
import com.fuzaylofficial.skyweb.ui.base.BaseViewModel
import com.fuzaylofficial.skyweb.ui.repository.PicturesRepository
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: PicturesRepository) : BaseViewModel() {

    //Кейс для Coroutines
    fun fetchPictures(): Flow<PagingData<Picture>> {
        return repository.letImagesFlow()
            .map { it.map { it } }
            .cachedIn(viewModelScope)
    }

    //Кейс для RxJava
    fun fetchPicturesObservable(): Observable<PagingData<Picture>> {
        return repository.letImagesObservable()
            .map { it.map { it } }
            .cachedIn(viewModelScope)
    }

    //Кейс для LiveData
    fun fetchPicturesLiveData(): LiveData<PagingData<Picture>> {
        return repository.letImagesLiveData()
            .map { it.map { it } }
            .cachedIn(viewModelScope)
    }

}