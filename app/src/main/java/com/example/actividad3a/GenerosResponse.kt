package com.example.actividad3a


import com.google.gson.annotations.SerializedName

class GenerosResponse : ArrayList<GenerosResponse.GenerosResponseItem>(){
    data class GenerosResponseItem(
        @SerializedName("tipo_genero")
        val tipoGenero: String,
        @SerializedName("url_imagen")
        val urlImagen: String
    )
}