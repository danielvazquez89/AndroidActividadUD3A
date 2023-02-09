package com.example.actividad3a.data.remotes

import com.example.actividad3a.data.models.UserRequest
import com.example.actividad3a.data.models.GenerosResponse
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.models.UsersResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("generos")
    fun getGeneros(): Call<GenerosResponse>

    @GET("juegos")
    fun getJuegos(): Call<JuegosResponse>

    @GET("juegos_favoritos")
    fun getJuegosFavoritos(): Call<JuegosResponse>

    @GET("juegos/genero/{genero}")
    fun getJuegosByGenre(
        @Path(value = "genero", encoded = false) key: String
    ): Call<JuegosResponse>

    @GET("users")
    fun getUsers(): Call<UsersResponse>

    @GET("users/{id}")
    fun getUserById(
        @Path(value = "id", encoded = false) key: Int
    ): Call<UsersResponse.UsersResponseItem>

    @Headers("Content-Type: application/json")
    @POST("users/")
    fun addUser(@Body userData: UserRequest): Call<UserRequest>

    @DELETE("users/")
    fun deleteUser(@Body userData: Int): Call<UserRequest>
/*
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genre: String): Call<MoviesByGenreResponse>
        */
}