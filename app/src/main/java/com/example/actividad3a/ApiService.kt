package com.example.actividad3a

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("generos")
    fun getGeneros(): Call<GenerosResponse>
/*
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genre: String): Call<MoviesByGenreResponse>
        */
}