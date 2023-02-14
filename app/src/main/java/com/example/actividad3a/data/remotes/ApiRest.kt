package com.example.actividad3a.data.remotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    //tu ip + 8080
    lateinit var retrofit: Retrofit
    val URL = "http://192.168.0.24:8080/"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
    init {
        initService()
    }
    fun initService() {
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}