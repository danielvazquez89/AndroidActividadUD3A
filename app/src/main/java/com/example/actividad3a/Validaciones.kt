package com.example.navigationcomponent

import android.text.TextUtils
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class Validaciones {
    fun esEmailValido (textFieldEmail: TextInputLayout): Boolean {
        var esValido = true
        val email = textFieldEmail.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            textFieldEmail.error = "El email no debe estar vacío"
            esValido = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textFieldEmail.error = "El email debe ser: usuario@dominio (juanito@gmail.com)"
            esValido = false
        } else {
            textFieldEmail.error = ""
        }
        return esValido
    }

    fun esNombreValido (textFieldNombre: TextInputLayout): Boolean {
        var esValido = true
        val nombre = textFieldNombre.editText?.text.toString()
        if (TextUtils.isEmpty(nombre)) {
            textFieldNombre.error = "Rellene el campo porfavor"
            esValido = false
        } else {
            var todoLetras = true
            for (i in 0..nombre.length-1) {
                if (!nombre[i].isLetter() && !nombre[i].isWhitespace()) {
                    todoLetras = false
                }
            }
            if (todoLetras) {
                textFieldNombre.error = ""
            } else {
                textFieldNombre.error = "El Nombre/Apellido debe estar compuesto unícamente de letras"
                esValido = false
            }
        }
        return esValido
    }

    fun esContrasenaValida (textFieldContrasena: TextInputLayout): Boolean {
        var esValido = true
        val contrasena = textFieldContrasena.editText?.text.toString()
        if (TextUtils.isEmpty(contrasena) || (contrasena.length < 8 || contrasena.length > 16)) {
            textFieldContrasena.error = "La contraseña debe ser de 8 a 16 caracteres"
            esValido = false
        } else if (contrasena.contains(' ')) {
            textFieldContrasena.error = "La contraseña no debe contener espacios"
            esValido = false
        } else {
            textFieldContrasena.error = ""
        }
        return esValido
    }
}