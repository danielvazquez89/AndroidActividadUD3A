package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class MensajeResponse : ArrayList<MensajeResponse.MensajeResponseItem>(){
    data class MensajeResponseItem(
        @SerializedName("contenido_mensaje")
        val contenidoMensaje: String,
        @SerializedName("id_chat")
        val idChat: Int,
        @SerializedName("id_mensaje")
        val idMensaje: Int
    )
}