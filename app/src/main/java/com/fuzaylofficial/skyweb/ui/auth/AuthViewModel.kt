package com.fuzaylofficial.skyweb.ui.auth

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fuzaylofficial.skyweb.model.Weather
import com.fuzaylofficial.skyweb.ui.base.BaseViewModel
import com.fuzaylofficial.skyweb.ui.repository.WeatherService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthViewModel @Inject constructor(var weatherService: WeatherService)  : BaseViewModel() {

    private val _text = MutableLiveData<Weather>()

    fun getWeahter(){
        viewModelScope.launch {
            try {
                _text.value = weatherService.getWeather()
            }catch (e: IOException){
                _text.value = null// "Error with loading"
            }catch (e: HttpException){
                _text.value = null//"Network Error"
            }
        }
    }

    val text: LiveData<Weather> = _text
}