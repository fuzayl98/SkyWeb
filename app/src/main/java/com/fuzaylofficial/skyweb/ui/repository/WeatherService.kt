package com.fuzaylofficial.skyweb.ui.repository

import com.fuzaylofficial.skyweb.model.Weather
import retrofit2.http.GET
interface WeatherService {
    @GET("weather?appid=c35880b49ff95391b3a6d0edd0c722eb&id=498817&lang=ru&units=metric")
    suspend fun getWeather(): Weather
}