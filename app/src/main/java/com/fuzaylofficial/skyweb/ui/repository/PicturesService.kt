package com.fuzaylofficial.skyweb.ui.repository

import com.fuzaylofficial.skyweb.model.Picture
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesService {

    @GET("list")
    suspend fun getImages(@Query("page") page: Int, @Query("limit") size: Int): List<Picture>
}