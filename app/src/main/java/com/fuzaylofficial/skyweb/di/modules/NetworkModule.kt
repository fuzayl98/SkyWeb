package com.fuzaylofficial.skyweb.di.modules

import com.fuzaylofficial.skyweb.Constants
import com.fuzaylofficial.skyweb.ui.repository.PicturesService
import com.fuzaylofficial.skyweb.ui.repository.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule{


    @Provides
    fun bindWeatherService(retrofit: Retrofit = getRetrofit()):WeatherService{
        return retrofit.newBuilder().
                baseUrl(Constants.WEATHER_URL)
            .client(getOkHttpClient(getOkHttpNetworkInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherService::class.java)
    }

    @Provides
    fun bindPicturesService(retrofit: Retrofit = getRetrofit()): PicturesService {
        return retrofit.create(PicturesService::class.java)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getOkHttpNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest =
                chain.request().newBuilder().header(Constants.HEADER_API_KEY, Constants.API_KEY).build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    fun getOkHttpClient(okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }

}