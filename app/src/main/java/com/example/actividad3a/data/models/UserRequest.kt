package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

data class UserRequest(
    val apellidos: String,
    val ciudad: String,
    @SerializedName("codigo_postal")
    val codigoPostal: String,
    val contrasena: String,
    val direccion: String,
    @SerializedName("fecha_nacimiento")
    val fechaNacimiento: String,
    val mail: String,
    val nombre: String
)