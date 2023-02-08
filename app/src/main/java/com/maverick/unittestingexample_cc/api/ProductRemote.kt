package com.maverick.unittestingexample_cc.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductRemote(

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null

)
