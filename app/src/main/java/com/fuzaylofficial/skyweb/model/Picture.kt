package com.fuzaylofficial.skyweb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Picture {

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("width")
    @Expose
    var width = 0

    @SerializedName("height")
    @Expose
    var height = 0

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("download_url")
    @Expose
    var downloadUrl: String? = null
}