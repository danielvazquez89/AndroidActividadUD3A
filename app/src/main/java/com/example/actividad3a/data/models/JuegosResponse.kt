package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class JuegosResponse : ArrayList<JuegosResponse.JuegosResponseItem>(){
    data class JuegosResponseItem(
        @SerializedName("descripcion_juego")
        val descripcionJuego: String,
        val genero: String,
        @SerializedName("id_producto")
        val idProducto: Int,
        @SerializedName("id_vendedor")
        val idVendedor: Int,
        @SerializedName("nombre_juego")
        val nombreJuego: String,
        val precio: Double,
        @SerializedName("url_imagen")
        val urlImagen: String
    ): java.io.Serializable
}