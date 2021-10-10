package com.example.json_app_currency

import com.google.gson.annotations.SerializedName

class Converence {
    @SerializedName("date")
    var date: String? = null
    @SerializedName("eur")
    var eur: aCurrency? = null
class aCurrency{
    @SerializedName("ada")
    var ada: String? = null
    @SerializedName("aed")
    var aed: String? = null
    @SerializedName("afn")
    var afn: String? = null
    @SerializedName("all")
    var all: String? = null
    @SerializedName("amd")
    var amd: String? = null
    @SerializedName("ang")
    var ang: String? = null
    @SerializedName("aoa")
    var aoa: String? = null
    @SerializedName("ars")
    var ars: String? = null
    @SerializedName("aud")
    var aud: String? = null
    @SerializedName("awg")
    var awg: String? = null
    @SerializedName("azn")
    var azn: String? = null
}
}