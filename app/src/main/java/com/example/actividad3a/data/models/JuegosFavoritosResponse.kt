package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class JuegosFavoritosResponse : ArrayList<JuegosFavoritosResponse.JuegosFavoritosResponseItem>(){
    data class JuegosFavoritosResponseItem(
        @SerializedName("fecha_like")
        val fechaLike: String,
        @SerializedName("id_producto")
        val idProducto: Int,
        @SerializedName("id_usuario")
        val idUsuario: Int
    )
}