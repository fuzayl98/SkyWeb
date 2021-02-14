package com.fuzaylofficial.skyweb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("name")
    @Expose
    var address: String? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    class Clouds {
        @SerializedName("all")
        @Expose
        var cloudly = 0

    }

    @SerializedName("main")
    @Expose
    var weahter: mainWeahter? = null

    class mainWeahter {
        @SerializedName("temp")
        @Expose
        var temp = 0f

        @SerializedName("humidity")
        @Expose
        var humidity = 0
    }
}