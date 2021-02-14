package com.fuzaylofficial.skyweb.customutlis

import com.fuzaylofficial.skyweb.model.Weather

object StringUtil {
    @JvmStatic
    fun authorText(author: String): String {
        return "Author: $author"
    }

    fun weatherToString(weather: Weather): String {
        return "Город : ${weather.address} ${weather.weahter?.temp.toString()} C" +
                "\nВлажность : ${weather.weahter?.humidity.toString()}%" +
                "Облачность : ${weather.clouds?.cloudly.toString()}%"
    }
}