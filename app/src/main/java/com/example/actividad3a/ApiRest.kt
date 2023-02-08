package com.example.actividad3a

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "http://212.0.109.168/"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
}