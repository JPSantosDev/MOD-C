package com.example.mod_c.ui.models

import android.icu.text.DateFormat
import androidx.datastore.preferences.core.booleanPreferencesKey

data class AppState(
    val splashScreenSeen: Boolean,
    val darkMode: Boolean,
    val notificationsActive: Boolean,
    val highContrast :Boolean

)

data class CarouselDto(
    val id: Int,
    val titulo: String,
    val descricao:String,
    val data: Int
)

data class CarouselItems(
    val id: Int,
    val titulo: String,
    val descricao:String,
    val data:Int,
)