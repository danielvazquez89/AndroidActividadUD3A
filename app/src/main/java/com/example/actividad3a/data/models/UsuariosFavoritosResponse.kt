package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class UsuariosFavoritosResponse : ArrayList<UsuariosFavoritosResponse.UsuariosFavoritosResponseItem>(){
    data class UsuariosFavoritosResponseItem(
        @SerializedName("fecha_like")
        val fechaLike: String,
        @SerializedName("id_usuario")
        val idUsuario: Int,
        @SerializedName("id_vendedor")
        val idVendedor: Int
    )
}