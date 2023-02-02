package com.maverick.unittestingexample_cc.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductListItem (

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null

)