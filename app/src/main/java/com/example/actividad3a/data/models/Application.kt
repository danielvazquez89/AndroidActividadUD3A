package com.example.actividad3a.data.models
import android.app. Application

open class Application : Application() {
    init {
        instance = this
    }
    companion object {
        lateinit var instance: Application
    }
}
