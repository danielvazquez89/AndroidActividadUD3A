package com.example.actividad3a

data class genre_content (
    val fotoGenero: String,
    val nombreGenero: String
)

data class game_content (
    val fotoJuego: String,
    val nombreJuego: String
)

data class user_content (
    val fotoUsuario: String,
    val nombreUsuario: String
)

data class chat_content(
    val fotoJuego: String,
    val fechaChat:String,
    val nombreProducto: String,
    val previewMensaje: String
)

data class message_content(
    val textoMensaje: String,
    val fechaMensaje:String,

)

data class settings_content(
    val textoSettings: String
)

data class compra_content(
    val fotoJuego: String,
    val textoProducto:String,
    val textoVendedor:String,
    val textoFecha: String,
    val textoPrecio: String
)

data class venta_content(
    val fotoJuego: String,
    val textoProducto:String,
    val textoComprador:String,
    val textoFecha: String,
    val textoPrecio: String
)

data class monedero_content(
    val textoDinero: String,
)