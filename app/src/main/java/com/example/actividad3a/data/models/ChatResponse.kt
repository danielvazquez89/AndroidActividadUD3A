package com.example.actividad3a.data.models


import com.google.gson.annotations.SerializedName

class ChatResponse : ArrayList<ChatResponse.ChatResponseItem>(){
    data class ChatResponseItem(
        @SerializedName("id_chat")
        val idChat: Int,
        @SerializedName("id_producto")
        val idProducto: Int,
        @SerializedName("id_usuario")
        val idUsuario: Int,
        @SerializedName("id_vendedor")
        val idVendedor: Int
    )
}