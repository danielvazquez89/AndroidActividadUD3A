package com.example.actividad3a

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("generos")
    fun getGeneros(): Call<GenerosResponse>

    @GET("users")
    fun getUsers(): Call<UsersResponse>

    @GET("users/{id}")
    fun getUserById(
        @Path(value = "id", encoded = false) key: Int
    ): Call<UsersResponse.UsersResponseItem>
/*
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genre: String): Call<MoviesByGenreResponse>
        */
}