package com.example.actividad3a.data.models

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


object Preferences {
    private const val USER_SETTINGS = "userSettings"

    private fun getEncryptedSharedPrefs (): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            masterKeyAlias,
            "user_prefs",
            Application .instance,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme .AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme .AES256_GCM
        )
    }
    fun getUserId(): String? {
        val userSettings = getEncryptedSharedPrefs().getString( USER_SETTINGS , null)
        return userSettings
    }
    fun setUserId(id: Int?) {
        getEncryptedSharedPrefs().edit().putString( USER_SETTINGS , id.toString()).apply()
    }
}
