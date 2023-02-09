package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class UsersResponse : ArrayList<UsersResponse.UsersResponseItem>(){
    data class UsersResponseItem(
        val apellidos: String,
        val ciudad: String,
        @SerializedName("codigo_postal")
        val codigoPostal: Int,
        val contrasena: String,
        val direccion: String,
        @SerializedName("fecha_nacimiento")
        val fechaNacimiento: String,
        @SerializedName("id_usuario")
        val idUsuario: Int?,
        val mail: String,
        val nombre: String,
        @SerializedName("saldo_monedero")
        val saldoMonedero: Double?
    )
}