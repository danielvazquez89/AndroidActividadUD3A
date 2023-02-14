package com.example.actividad3a.data.remotes

import com.example.actividad3a.data.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("generos")
    fun getGeneros(): Call<GenerosResponse>

    @GET("juegos")
    fun getJuegos(): Call<JuegosResponse>

    @GET("juegos/{id}")
    fun getJuegoById(
        @Path(value = "id", encoded = false) key: Int
    ): Call<JuegosResponse.JuegosResponseItem>

    @GET("users/email/{mail}")
    fun getUserByEmail(
        @Path(value = "mail", encoded = false) key: String
    ): Call<UsersResponse.UsersResponseItem>

    @GET("juegos_favoritos/{id}")
    fun getJuegosFavoritosById(
        @Path(value = "id", encoded = false) key: Int
    ): Call<JuegosFavoritosResponse>

    @GET("usuarios_favoritos/{id}")
    fun getUsuariosFavoritosByUserId(
        @Path(value = "id", encoded = false) key: Int
    ): Call<UsuariosFavoritosResponse>

    @DELETE("juegos_favoritos/{id}")
    fun deleteJuegoFavorito(
        @Path(value = "id", encoded = false) key: Int
    ): Call<JuegosFavoritosResponse>

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

    @Headers("Content-Type: application/json")
    @POST("juegos/")
    fun addJuego(@Body juegoData: JuegosResponse.JuegosResponseItem): Call<JuegosFavoritosResponse.JuegosFavoritosResponseItem>

    @DELETE("users/")
    fun deleteUser(@Body userData: Int): Call<UserRequest>
/*
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genre: String): Call<MoviesByGenreResponse>
        */
}